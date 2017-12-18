<style lang="less">
    @import './agentsetting.less';
</style>

<template>
    <div>
        <div class="searchcontent">
            <Row>
                    <Col span="24" style="text-align:right;margin-bottom:10px">
                        <Button type="primary" @click="addagent=true">添加代理商级别</Button>
                    </Col>
            </Row>
            <Row>
                <!-- <Table border :columns="agentColumns" :data="agentData" class="agentlisttable"></Table> -->
                <can-edit-table refs="agentsetting" v-model="agentData" :columns-list="agentColumns" @on-change="saveEdit"></can-edit-table>
            </Row>
        </div>
        <Modal
            v-model="addagent"
            title="添加代理商等级">
            <Form :model="agentinfo" :label-width="80">
              <FormItem label="等级名称">
                  <Input v-model="agentinfo.levelName" placeholder="等级名称"></Input>
              </FormItem>
              <FormItem label="邀请奖励">
                  <Input v-model="agentinfo.inviteAward" placeholder="邀请奖励只能输正整数或0"></Input>
              </FormItem>
              <FormItem label="拿货量">
                  <Input v-model="agentinfo.partnerNum" placeholder="拿货数量必须为正整数或0"></Input>
              </FormItem>
              <FormItem label="错误提示" v-if="agentinfo.msg">
                <div class="msg" style="color:red" v-text="agentinfo.msg"></div>
              </FormItem>
              <FormItem label="">
                  <Button type="primary" @click="saveAgent">保存</Button>
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
    name: 'agentsetting',
    components:{canEditTable},
    data () {
        return {
        addagent:false,
        agentinfo:{
          inviteAward: 0,
          levelName: "",
          partnerNum: 0,
          msg:""
        },
        queryinfo:{
          userQuery:'',
          agentType:''
        },
         agentType:[
          {
              value: 'CORE_agent',
              label: '核心股东'
          }
         ],
         agentData:[],
         agentColumns:[
           {
            title: '序号',
            type: 'index',
            align: 'center'
          },
          {
            title:'级别名称',
            key: 'levelName',
            align:'center',
            editable:true
          },
          {
            title:'邀请奖励',
            key: 'inviteAward ',
            align:'center',
            editable: true
          },
          {
            title:'拿货量',
            key: 'partnerNum',
            align:'center',
            editable: true
          },
          {
            title:'产品名',
            key: 'product.name',
            align:'center'
          },
          {
            title: '产品图',
            key: 'pic',
            align:'center',
            editable: true
          },
          {
            title: '统一销售价',
            key: 'product.sellPrice',
            align:'center'
          },
          {
            title: '补货周期',
            key: 'productDays',
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
          this.getagentSetting()
        },
        getagentSetting () {
          this.http.get('/partnerLevel').then(res=>{
            if(res.error==false){
              res.result.forEach(element => {
                
              });
              this.agentData=res.result
            }
          })
        },
        saveEdit(info,b){
          var setting=info[b]
          this.http.post('/partnerLevel/update',setting).then(res=>{
            if(res.error==false){
              this.$Message.success("修改成功")
              this.getagentSetting()
            }else{
              this.$Message.error(res.msg)
            }
          })
        },
        saveAgent(){
          if(!this.agentinfo.levelName){
            this.agentinfo.msg="等级名称不能为空"
            return;
          }

          if(this.agentinfo.inviteAward<1){
            this.agentinfo.msg="邀请奖励必须为大于1的数字"
            return
          }

          if(this.agentinfo.partnerNum<1){
            this.agentinfo.msg="拿货数量必须为大于1"
            return
          }

          this.agentinfo.msg=""
          var param=JSON.parse(JSON.stringify(this.agentinfo))
          delete param.msg
          this.http.post('/partnerLevel',param).then(res=>{
            if(res.error==false){
              this.$Message.success("新增成功")
              this.getagentSetting()
              this.addagent=false
              this.agentinfo={
                inviteAward: 0,
                levelName: "",
                partnerNum: 0,
                msg:""
              }
            }else{
              this.$Message.error(res.msg)
              this.agentinfo.msg=res.msg
            }
          })
        }
    },
    mounted () {
        this.init();
    }
};
</script>
