<style lang="less">
    @import './groupsetting.less';
</style>

<template>
    <div>
        <div class="searchcontent">
            <Row>
                    <Col span="24" style="text-align:right;margin-bottom:10px">
                        <Button type="primary" @click="addgroup=true">添加团队奖励设置</Button>
                    </Col>
            </Row>
            <Row>
                <!-- <Table border :columns="groupColumns" :data="groupData" class="grouplisttable"></Table> -->
                <can-edit-table refs="groupsetting" v-model="groupData" :columns-list="groupColumns" @on-change="saveEdit"></can-edit-table>
            </Row>
        </div>
        <Modal
            v-model="addgroup"
            title="添加团队奖励设置">
            <Form :model="groupinfo" :label-width="80">
              <FormItem label="股东类型">
                  <Select v-model="groupinfo.holderType" style="width:200px">
                    <Option v-for="item in holderType" :value="item.value" :key="item.value">{{ item.label }}</Option>
                </Select>
              </FormItem>
              <FormItem label="奖励金额">
                  <Input v-model="groupinfo.award" placeholder="邀请奖励只能输正数"></Input>
              </FormItem>
              <FormItem label="产品数量">
                  <Input v-model="groupinfo.productNum" placeholder="产品数量必须为正整数或0"></Input>
              </FormItem>
              <FormItem label="团队业绩奖励比率">
                  <Input v-model="groupinfo.teamAwardRate" placeholder="0-100的数字"></Input>
              </FormItem>
              <FormItem label="公司业绩奖励比率">
                  <Input v-model="groupinfo.totalAwardRate" placeholder="0-100的数字"></Input>
              </FormItem>
              <FormItem label="错误提示" v-if="groupinfo.msg">
                <div class="msg" style="color:red" v-text="groupinfo.msg"></div>
              </FormItem>
              <FormItem label="">
                  <Button type="primary" @click="savegroup">保存</Button>
              </FormItem>

            </Form>
            <div slot="footer"></div>
        </Modal>
    </div>
</template>

<script>
import Util from '../../libs/util'
import canEditTable from '../tables/components/canEditTable.vue';
export default {
    name: 'groupsetting',
    components:{canEditTable},
    data () {
        return {
        addgroup:false,
        groupinfo:{
          award: "",
          holderType: "",
          productNum: 0,
          teamAwardRate:"",
          totalAwardRate:"",
          msg:""
        },
        queryinfo:{
          userQuery:'',
          groupType:''
        },
         holderType:[
          {
              value: 'CORE_group',
              label: '核心股东'
          },{
            value:'MORMAL_HOLDER',
            label:'普通股东'
          }
         ],
         groupData:[],
         groupColumns:[
           {
            title: '序号',
            type: 'index',
            align: 'center'
          },
          {
            title:'股东类型',
            key: 'holderType',
            align:'center',
            editable:true
          },
          {
            title:'奖励金额',
            key: 'award ',
            align:'center',
            editable: true
          },
          {
            title:'产品数量',
            key: 'productNum',
            align:'center',
            editable: true
          },
          {
            title:'团队业绩奖励比率(%)',
            key: 'teamAwardRate',
            align:'center',
            editable: true
          },
          {
            title: '公司业绩奖励比例(%)',
            key: 'totalAwardRate',
            align:'center',
            editable: true
          },
          {
              title: '操作',
              align: 'center',
              key: 'handle',
              width:'190',
              handle: ['edit','delete']
          }
         ]
        }
    },
    methods: {
        init () {
          this.getgroupSetting()
        },
        getgroupSetting () {
          this.http.get('/holderTeamAward').then(res=>{
            if(res.error==false){
              res.result.forEach(element => {
                
              });
              this.groupData=res.result
            }
          })
        },
        saveEdit(info,b){
          var setting=info[b]
          this.http.post('/holderTeamAward/update',setting).then(res=>{
            if(res.error==false){
              this.$Message.success("修改成功")
              this.getgroupSetting()
            }else{
              this.$Message.error(res.msg)
            }
          })
        },
        savegroup(){
          if(!this.groupinfo.holderType){
            this.groupinfo.msg="股东类型需选择"
            return;
          }

          if(this.groupinfo.award<0){
            this.groupinfo.msg="奖励金额必须大于0"
            return
          }

          if(this.groupinfo.productNum<0){
            this.groupinfo.msg="产品数量必须为大于0"
            return
          }

          if(this.groupinfo.teamAwardRate<0||this.groupinfo.teamAwardRate>100){
            this.groupinfo.msg="团队业绩奖励比率必须为0-100之间"
            return
          }

          if(this.groupinfo.totalAwardRate <0||this.groupinfo.totalAwardRate >100){
            this.groupinfo.msg="公司业绩奖励比率必须为0-100之间"
            return
          }
          this.groupinfo.msg=""
          var param=JSON.parse(JSON.stringify(this.groupinfo))
          delete param.msg
          this.http.post('/holderTeamAward/add',param).then(res=>{
            if(res.error==false){
              this.$Message.success("新增成功")
              this.getgroupSetting()
              this.addgroup=false
              this.groupinfo={
                award: "",
                holderType: "",
                productNum: 0,
                teamAwardRate:"",
                totalAwardRate:"",
                msg:""
              }
            }else{
              this.$Message.error(res.msg)
              this.groupinfo.msg=res.msg
            }
          })
        }
    },
    mounted () {
        this.init();
    }
};
</script>
