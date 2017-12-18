<style lang="less">
    @import './allholder.less';
</style>

<template>
    <div>
        <div class="searchcontent">
            <Row>
                <Col span="4" offset="14" style="padding:0px 5px;">
                 <Select style="width:100%" v-model="queryinfo.holderType">
                    <Option v-for="item in holderType" :value="item.value" :key="item.value">{{ item.label }}</Option>
                </Select>
                </Col>                
                <Col span="4" style="padding:0px 5px;">
                <Input type="text" placeholder="查询真实姓名或者手机号码" v-model="queryinfo.userQuery"></Input>
                </Col>
                <Col span="2">
                    <Button type="primary" @click="getHolderlist(1)">查询</Button>
                </Col>
            </Row>
            <Row>
                <Table border :columns="holderColumns" :data="holderData" class="holderlisttable"></Table>
              <div style="margin: 10px;overflow: hidden">
                  <div style="float: right;">
                      <Page :total="holderpager.total" :page-size="holderpager.size" :current="holderpager.number" @on-change="getHolderlist"></Page>
                  </div>
              </div>
            </Row>
        </div>
    </div>
</template>

<script>
import Util from '../../libs/util'
export default {
    name: 'allholder',
    data () {
        return {
        queryinfo:{
          userQuery:'',
          holderType:''
        },
         holderType:[
          {
              value: 'CORE_HOLDER',
              label: '核心股东'
          },{
            value:'MORMAL_HOLDER',
            label:'普通股东'
          }
         ],
         holderpager:{
           size:15,
           number:1,
           totalPages:1,
         },
         holderData:[],
         holderColumns:[
           {
            title: '序号',
            type: 'index',
            align: 'center'
          },
          {
            title:'头像',
            key: 'user.headImg'
          },
          {
            title:'真实姓名',
            key: 'user.realName'
          },
          {
            title:'产品数',
            key: 'user.totalProductNum'
          },
          {
            title:'代理等级',
            key: 'partnerLevel.partnerNum'
          },
          {
            title: '股东类型',
            key: 'holderType'
          },{
            title: '邀请码',
            key: 'inviteCode'
          }
         ]
        }
    },
    methods: {
        init () {
          this.getHolderlist()
        },
        getHolderlist (pageno) {
          var param={
            size:this.holderpager.size,
            page: pageno||1,
            userQuery:this.queryinfo.userQuery,
            holderType:this.queryinfo.holderType
          }
          this.http.get('/holder?'+Util.parseParam(param)).then(res=>{
            if(res.error==false){
              if(res.result.number==0){
                res.result.number = 1
              } 
              this.holderpager=res.result
              this.holderData=res.result.content
            }
          })
        }
    },
    mounted () {
        this.init();
    }
};
</script>
