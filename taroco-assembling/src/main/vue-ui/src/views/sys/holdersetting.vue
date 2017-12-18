<style lang="less">
    @import './holdersetting.less';
</style>

<template>
    <div>
        <div class="searchcontent">
            <Row>
                <!-- <Table border :columns="holderColumns" :data="holderData" class="holderlisttable"></Table> -->
                <can-edit-table refs="holdersetting" v-model="holderData" :columns-list="holderColumns" @on-change="saveEdit"></can-edit-table>
            </Row>
        </div>
    </div>
</template>

<script>
import Util from '../../libs/util'
import canEditTable from '../tables/components/canEditTable.vue';
export default {
    name: 'holdersetting',
    components:{canEditTable},
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
          }
         ],
         holderData:[],
         holderColumns:[
           {
            title: '序号',
            type: 'index',
            align: 'center'
          },
          {
            title:'股东类型',
            key: 'holderTypeTxt',
            align:'center'
          },
          {
            title:'等级循环拿货数量',
            key: 'cycleProductNum',
            align:'center',
            editable: true
          },
          {
            title:'分红比率',
            key: 'inviteAwardPercent',
            align:'center',
            editable: true
          },
          {
            title:'第几人分红',
            key: 'inviteAwardUserNum',
            align:'center',
            editable: true
          },
          {
            title: '补货数量下限',
            key: 'lessProductPercent',
            align:'center',
            editable: true
          },
          {
            title: '补货数量上限',
            key: 'moreProductPercent',
            align:'center',
            editable: true
          },
          {
            title: '补货周期',
            key: 'productDays',
            align:'center',
            editable: true
          },{
            title: '拿货数量',
            key: 'productNum',
            align:'center',
            editable: true
          },
          {
              title: '操作',
              align: 'center',
              key: 'handle',
              handle: ['edit']
          }
         ]
        }
    },
    methods: {
        init () {
          this.getHolderSetting()
        },
        getHolderSetting () {
          this.http.get('/holderSetting').then(res=>{
            if(res.error==false){
              res.result.forEach(element => {
                element.holderTypeTxt=element.holderType=='CORE_HOLDER'?'核心股东':element.holderType=='MORMAL_HOLDER'?'普通股东':''
              });
              this.holderData=res.result
            }
          })
        },
        saveEdit(info,b){
          var setting=info[b]
          if(setting.cycleProductNum<0){
            this.$Message.error("等级循环拿货数量不能小于0")
            return
          }
          if(setting.inviteAwardPercent<0){
            this.$Message.error("分红比率数量不能小于0")
            return
          }
          if(setting.inviteAwardUserNum<0){
            this.$Message.error("第几人分红数量不能小于0")
            return
          }

          if(setting.lessProductPercent<0){
            this.$Message.error("补货数量下限数量不能小于0")
            return
          }
          if(setting.moreProductPercent<0){
            this.$Message.error("补货数量上限数量不能小于0")
            return
          }
          if(setting.cycleProductNum<0){
            this.$Message.error("补货周期不能小于0")
            return
          }
          if(setting.productNum<0){
            this.$Message.error("拿货数量不能小于0")
            return
          }
          
          this.http.post('/holderSetting',setting).then(res=>{
            if(res.error==false){
              this.$Message.success("修改成功")
              this.getHolderSetting()
            }else{
              this.$Message.error(res.msg)
            }
          })
        }
    },
    mounted () {
        this.init();
    }
};
</script>
