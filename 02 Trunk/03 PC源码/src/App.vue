<template>
	<div id="app">
		<router-view></router-view>
	</div>
</template>

<script>
    export default {
        name: "App",
    };
</script>

<style lang="scss" type="text/scss">
	@import "assets/sass/global";
	#app{
		width: 100%;
		height: 100%;
	}
</style>
