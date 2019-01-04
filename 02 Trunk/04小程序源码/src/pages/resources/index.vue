<template>
  <div class="container">
    <div class="resource-warp clearfix">
      <template v-for="(item, index) in resList">
        <div class="res-item fl" :key="'res_items_' + index">
          <img v-if="item.logoUrl" class="res-item__headimg" :src="logoUrl" alt="">
          <img v-else class="res-item__headimg" src="../../assets/images/resource_init.png" alt="">
          <div class="res-name">{{item.name}}</div>
        </div>
      </template>
    </div>
  </div>
</template>

<script>

export default {
  data () {
    return {
      resList: []
    }
  },
  computed: {
  },
  methods: {
  },
  mounted () {
    // 请求资源列表
    this.getResourseListData();
  },
  methods: {
    getResourseListData () {
      this.$http({
        url: '/rs/def/getall.do',
        method: 'get',
        header: {
          'content-type': 'application/x-www-form-urlencoded'
        },
        success: res => {
          console.log(res);
          this.resList = res.data;
        }
      });
    }
  }
}
</script>

<style lang="scss">
page {
  background-color: #fff;
}
.counter-warp {
  text-align: center;
  margin-top: 100px;
}
.home {
  display: inline-block;
  margin: 100px auto;
  padding: 5px 10px;
  color: blue;
  border: 1px solid blue;
}
.container {
  padding: 15px;
}
.resource-warp {
  margin-left: -7.5px;
  margin-right: -7.5px;
}
.res-item {
  width: 33.3333333%;
  padding: 0 7.5px 10px;
  text-align: center;
  .res-item__headimg {
    width: 105px;
    height: 105px;
    vertical-align: bottom;
  }
  .res-name {
    font-size: 15px;
  }
}
</style>
