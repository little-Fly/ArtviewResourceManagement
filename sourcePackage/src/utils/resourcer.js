import { HostUrl } from '@/config/api';

    /*
    *从接口拿到的资源数据是一维平铺，这里根据sourceKey进行二维处理
    */
export function $resourcer(data){
      var saList = [];
      var dList = [];
      for(;;){
        if(data.length < 1)break;
        var key = data[0].resourceKey;
        var i=0;
        for(;;){
          if(key == data[i].resourceKey){
            data[i].attrLevel = getAuthorizeWord(data[i].attrLevel);
            dList.push(data[i]);
            data.splice(i,1);
          }
          else i++;
          if(data.length == 0)break;
          if(i >= data.length)break;
        }
        saList.push(dList);
        dList = [];
      }
      saList = sequnce(saList);
      return saList;
}
//把图片、视频放到最后去
function sequnce(saList){
    for(var i=0; i<saList.length; i++){
      var len = saList[i].length;
      if(len > 1){
        for(var j=0; j<(len-1); j++){
          if(saList[i][j].attrType == 'picture' || saList[i][j].attrType == 'video'){
            saList[i][j].attrValue = HostUrl + saList[i][j].attrValue; // 给图片视频加上host路径
            if(j == (len-2)){
              if(saList[i][len-1].attrType != 'picture' && saList[i][len-1].attrType != 'video'){
                saList[i].push(saList[i][j]);
                saList[i].splice(j,1);
              }
            }
            else if(j != (len-1)){
                saList[i].push(saList[i][j]);
                saList[i].splice(j,1);
                j--;
            }
          }
         }
       }
    }
    return saList;
}
function getAuthorizeWord(attrLevel){
      if(attrLevel == 2)return "管理员";
      else if(attrLevel == 1)return "员工";
      return "任何人";
}
