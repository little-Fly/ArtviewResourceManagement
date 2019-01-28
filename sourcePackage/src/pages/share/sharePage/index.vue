<template>
  <web-view :src="tpltUrl"></web-view>
</template>

<script>

export default {
  name: 'sharePage',
  data () {
    return {
      tpltUrl: '',
    }
  },
  mounted () {
    let paramsObj = this.$tool.getOptions();
    this.tpltUrl = paramsObj.tpltUrl + "?shareKey=" + paramsObj.shareKey;
  },
}
</script>

<style lang=scss>
  .box{
    
  }
</style>
