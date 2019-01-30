<template>
  <div class="condition-box">
    <div class="condition-list">
        <checkbox-group @change="selecteConditionCheck">
      <template v-for="(item, index) in attrList">
        <div class="condition-item"  :key='index'>
          <span>{{item.name}}</span>
          <span class="condition-equal">：</span>
          <input class="input-value"  placeholder="请输入关键字"  v-model="item.word" maxlength="16" >
          <checkbox class="condition-check" :checked="item.check"   :value="index" />
        </div>
      </template>
        </checkbox-group>
    </div>
    <div class="add-condition-box">
      <span class="add-condition-btn" @click="addConditionBtn()">增加条件</span>
    </div>
  </div>
</template>

<script>

export default {
  data () {
    return {
      attrList: [],//属性字定义，及已有匹配词
      searchList:[],//已有搜索字列表
    }
  },
  computed: {

  },
  mounted () {
    let paramsObj = this.$tool.getOptions();
    var al = JSON.parse(paramsObj.attrNames).names;
    this.searchList = this.cutTheResearchWord();
    this.createSearchVariable(al);
  },
  methods: {
    //
    createSearchVariable(al) {
      this.attrList = [];
      for(var i=0; i<al.length; i++){
        var worded='';
        for(var j=0; j<this.searchList.length; j++){
          if(this.searchList[j].name == al[i]){
            worded = this.searchList[j];
            break;
          }
        }
        this.attrList.push({
          name: al[i],
          word: worded?worded.word : '',
          check: worded?false : true
        });
      }
    },
    addConditionBtn () {
      for(var i=0; i<this.attrList.length; i++){
        if(this.attrList[i].check){
          if(this.attrList[i].word.length < 1){
            wx.showModal({title: '提示', content: '每个勾选的条目都要输入关键字', showCancel: false});
            return;
          }
        }
      }
      for(var i=0; i<this.attrList.length; i++){
        if(this.attrList[i].check){
          this.saveSerchWord(this.attrList[i]);
        }
      }
      wx.navigateBack({result: 1});
    },
    selecteConditionCheck(e){
      for(var i=0; i<this.attrList.length; i++){this.attrList[i].check = false;}
      for(var i = 0; i<e.target.value.length; i++){
          this.attrList[e.target.value[i]].check = true;
      }
    },
    /*
     *把一组搜索结构化数据转化成字符串存入全局变量
     */
    saveSerchWord(word){
      if(this.$store.state.rsResearchWord.length > 1)this.$store.state.rsResearchWord += "，";
      this.$store.state.rsResearchWord += word.name + "：" + word.word;
    },
    /*
     *把全局存放的搜索字符串结构化
     */
    cutTheResearchWord(){
      var words = this.$store.state.rsResearchWord;
      words = words.replace(/ /g, '');
      words = words.replace(/,/g, '，');
      words = words.replace(/:/g, '：');
      var wList = words.split('，');
      var searchWordList = [];
      for(var i=0; i<wList.length; i++){
        var atList = wList[i].split('：');
        if(atList.length == 2){
          searchWordList.push({
            name: atList[0],
            word: atList[1]
          });
        }
      }
      return searchWordList;
    }
  }
}
</script>

<style lang="scss">
  page {
    background-color: #fff;
  }
  .add-condition-btn{
    color: white;
    background-color: #67c23a;
    display: inline-block;
    vertical-align: middle;
    border-radius: 4px;
    font-size: 15px;
    height: 30px;
    padding: 0 15px;
    line-height: 30px;
  }
  .add-condition-box{
    width: 100%;
    padding-top: 24px;
    text-align: center;
  }
  .condition-box {
    margin-bottom: 12px;
  }
  .condition-list{
    padding-bottom: 12px;
    margin: 0 16px;
    border-bottom: 1px solid #f56c6c;
  }
  .condition-item{
    display: flex;
    padding: 8px 0px;
    font-size: 15px;
  }
  .condition-equal{
    display: inline-block;
    margin: 0 16px;
  }
  .input-value{
    display: inline-block;
    width: 128px;
    height: 18px;
    border: 1px solid #aaaaaa;
    border-radius: 8px;
  }
  .condition-check{
    margin-left: 64px;
    width: 18px;
    height: 18px;
  }
</style>
