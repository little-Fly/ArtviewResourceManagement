<script>

export default {
  data () {
    return {

    }
  },
  created () {

  },
  methods: {
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {},
}
</script>

<style lang=scss>
@import './assets/zanui-css/zanui.wxss';
@import './assets/scss/app.scss';

page {
  min-height: 100%;
  background-color: #eee;
}
view {
  box-sizing: border-box;
}
.align-right{
  text-align: right;
}
.align-center{
  text-align: center;
}
.full-width {
  width: 100%
}
.inline-block {
  display: inline-block;
}
.lr-mg-5 {
  margin-left: 5px;
  margin-right: 5px;
}
.lr-mg-10 {
  margin-left: 10px;
  margin-right: 10px;
}
.fr {
  float: right;
}
.fl {
  float: left;
}
.clearfix {
  &::before,&::after {
    content: '';
    display: block;
    clear: both;
  }
}
/* 行内元素两边对其 */
.keith {
  text-align: justify;
  &::after {
    display: inline-block;
    width: 100%;
    content: '';
  }
}
</style>
