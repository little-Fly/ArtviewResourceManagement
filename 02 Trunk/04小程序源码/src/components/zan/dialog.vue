<template>
  <div class="zan-dialog" :class="{ 'zan-dialog--show': zanDialogObj.show === true }">
    <div class="zan-dialog--mask"></div>
    <div class="zan-dialog--container">
      <div
        v-if="zanDialogObj.title"
        class="zan-dialog__header">{{ zanDialogObj.title }}</div>
      <div
        class="zan-dialog__content" :class="{ 'zan-dialog__content--title' : title }">{{ zanDialogObj.content }}</div>
      <div
        class="zan-dialog__footer">
        <block v-for="(item, index) in zanDialogObj.buttons" :key="item.text + '-' + item.type">
          <button
            class="zan-dialog__button zan-btn"
            :data-type="item.type"
            @click="_handleZanDialogButtonClick(item.fnName)"
            :style="computedColor[index]">{{ item.text }}</button>
        </block>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    props: ['zanDialogObj'],
    data () {
      return {
        name: '',
        show: false
      }
    },
    computed: {
      computedColor: function () {
        if (!this.zanDialogObj.buttons) return null
        let arr = []
        for (let i = 0; i < this.zanDialogObj.buttons.length; i++) {
          const str = 'color: ' + (this.zanDialogObj.buttons[i].color || '#333')
          arr.push(str)
        }
        return arr
      }
    },
    methods: {
      _handleZanDialogButtonClick (fnName) {
        this.zanDialogObj.show = false;
        if (fnName) {
          this.$emit(fnName);
        }
      }
    }
  }
</script>
<style scoped>

</style>
