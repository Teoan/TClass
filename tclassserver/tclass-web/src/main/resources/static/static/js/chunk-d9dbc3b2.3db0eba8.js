(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-d9dbc3b2"],{"613e":function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("span",{staticClass:"workMana-div"},[a("el-card",{staticClass:"box-card-workdata",attrs:{shadow:"hover","body-style":{height:"100%"}}},[a("el-input",{staticStyle:{width:"30%","margin-top":"20px"},attrs:{placeholder:"请输入作业名字搜索"},nativeOn:{keydown:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.selectWorkByName(t)}},model:{value:e.selectWorkName,callback:function(t){e.selectWorkName=t},expression:"selectWorkName"}}),a("el-date-picker",{staticStyle:{"margin-left":"20px","margin-top":"20px"},attrs:{align:"right",type:"date","value-format":"yyyy-MM-dd HH:mm:ss",placeholder:"选择发布日期","picker-options":e.pickerOptions},model:{value:e.selectCreateTime,callback:function(t){e.selectCreateTime=t},expression:"selectCreateTime"}}),a("el-button",{staticStyle:{"margin-left":"20px","margin-top":"20px"},attrs:{type:"primary",icon:"el-icon-search"},on:{click:e.selectWorkByName}},[e._v("搜索")]),a("el-button",{staticStyle:{"margin-left":"20px","margin-top":"20px"},attrs:{type:"primary",icon:"el-icon-edit"},on:{click:function(t){return e.showDialog("发布")}}},[e._v("发布作业")]),a("el-dialog",{attrs:{title:e.dialogTitle,visible:e.dialogSelectVisible,width:"30%"},on:{"update:visible":function(t){e.dialogSelectVisible=t},close:e.initData}},[a("el-form",{directives:[{name:"loading",rawName:"v-loading",value:e.workFormLoading,expression:"workFormLoading"}],ref:"workDataForm",attrs:{rules:e.rules,model:e.workData,"label-width":"auto","status-icon":!0,"label-position":"left"}},[a("el-form-item",{attrs:{label:"作业名称",prop:"name"}},[a("el-input",{model:{value:e.workData.name,callback:function(t){e.$set(e.workData,"name",t)},expression:"workData.name"}})],1),a("el-form-item",{attrs:{label:"文件命名格式",prop:"fileNameFormatEnum"}},[a("el-tooltip",{attrs:{content:"若选择无，则不强制修改用户提交文件的文件名",placement:"right"}},[a("el-select",{attrs:{placeholder:"请选择文件命名格式"},model:{value:e.workData.fileNameFormatEnum,callback:function(t){e.$set(e.workData,"fileNameFormatEnum",t)},expression:"workData.fileNameFormatEnum"}},e._l(e.fileNameFormats,(function(e){return a("el-option",{key:e.enum,attrs:{label:e.format,value:e.enum}})})),1)],1)],1),a("el-form-item",{attrs:{label:"文件扩展名",prop:"extensionId"}},[a("el-tooltip",{attrs:{content:"将对用户上传的文件进行扩展名验证",placement:"right"}},[a("el-select",{attrs:{placeholder:"请选择文件扩展名"},model:{value:e.workData.extensionId,callback:function(t){e.$set(e.workData,"extensionId",t)},expression:"workData.extensionId"}},e._l(e.extensions,(function(e){return a("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})})),1)],1)],1),a("el-form-item",{attrs:{label:"截止提交时间",prop:"lastTime"}},[a("el-tooltip",{attrs:{content:"超过截止提交时间，则用户无法提交文件",placement:"right"}},[a("el-date-picker",{attrs:{"value-format":"yyyy-MM-dd HH:mm:ss",type:"datetime",placeholder:"选择日期时间","picker-options":e.WorkLastTimePickerOptions},model:{value:e.workData.lastTime,callback:function(t){e.$set(e.workData,"lastTime",t)},expression:"workData.lastTime"}})],1)],1),a("el-form-item",{attrs:{label:"备注"}},[a("el-input",{attrs:{type:"textarea",autosize:{minRows:2,maxRows:4},placeholder:"请输入内容"},model:{value:e.workData.remarks,callback:function(t){e.$set(e.workData,"remarks",t)},expression:"workData.remarks"}})],1)],1),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[e.isEditWork?a("el-button",{attrs:{type:"primary"},on:{click:e.doEditWork}},[e._v("完成编辑")]):a("el-button",{attrs:{type:"primary"},on:{click:e.doAddWork}},[e._v("发布")])],1)],1),a("el-button",{staticStyle:{"margin-left":"20px","margin-top":"20px"},attrs:{type:"primary",icon:"el-icon-edit"},on:{click:function(t){e.dialogExtensionVisible=!0}}},[e._v("编辑扩展名列表")]),a("el-dialog",{attrs:{title:"编辑扩展名列表",visible:e.dialogExtensionVisible,width:"18%"},on:{"update:visible":function(t){e.dialogExtensionVisible=t},close:function(t){return e.whenDialogExtensionClose("extensionValidateForm")}}},[a("el-form",{ref:"extensionValidateForm",attrs:{"label-width":"auto",model:e.extensionValidateForm,inline:!0,"status-icon":!0}},[a("el-form-item",{attrs:{prop:"extension",label:"扩展名",rules:[{required:!0,message:"请输入扩展名",trigger:"blur"},{pattern:/^\.[0-9a-zA-Z]+$/,message:"请输入正确的扩展名",trigger:["blur","change"]}]}},[a("el-input",{attrs:{size:"small"},model:{value:e.extensionValidateForm.extension,callback:function(t){e.$set(e.extensionValidateForm,"extension",t)},expression:"extensionValidateForm.extension"}})],1),a("el-form-item",[a("el-button",{attrs:{size:"small",type:"primary"},on:{click:e.addExtension}},[e._v("添加")])],1)],1),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.extensionTableLoading,expression:"extensionTableLoading"}],staticStyle:{width:"100%"},attrs:{data:e.extensions,"max-height":"200px","row-class-name":e.tableRowClassName}},[a("el-table-column",{attrs:{prop:"name",label:"扩展名",width:"200"}}),a("el-table-column",{attrs:{label:"操作","min-width":"80",fixed:"right"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("div",{staticClass:"cell-contain"},[a("el-button",{attrs:{size:"small",type:"danger",disabled:1===t.row.id},on:{click:function(a){return e.deleteExtension(t.row)}}},[e._v("删除")])],1)]}}])})],1),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.dialogExtensionVisible=!1}}},[e._v("完成编辑")])],1)],1),e.multipleSelection.length>0?[a("el-popconfirm",{attrs:{title:"删除作业会将已提交的文件一并删除"},on:{onConfirm:e.deleteWorks}},[a("el-button",{staticStyle:{"margin-left":"20px"},attrs:{slot:"reference",type:"danger",icon:"el-icon-delete"},slot:"reference"},[e._v("批量删除")])],1)]:e._e(),a("div",{staticClass:"table-div"},[a("el-tooltip",{attrs:{content:"选中可进行批量删除",placement:"top-start"}},[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.tableLoading,expression:"tableLoading"}],staticStyle:{width:"100%"},attrs:{data:e.workDataList,height:"100%",border:""},on:{"selection-change":e.handleSelectionChange}},[a("el-tooltip",{attrs:{content:"选中可进行批量删除",placement:"top"}},[a("el-table-column",{attrs:{type:"selection",width:"55"}})],1),a("el-table-column",{attrs:{fixed:"",prop:"name",label:"作业名称","min-width":"100"}}),a("el-table-column",{attrs:{prop:"fileNameFormatEnum",formatter:e.fileNameFormat,label:"文件名规则","min-width":"80"}}),a("el-table-column",{attrs:{prop:"extensionName",label:"文件扩展名","min-width":"60"}}),a("el-table-column",{attrs:{prop:"lastTime",label:"截止提交时间","min-width":"100"}}),a("el-table-column",{attrs:{prop:"remarks",label:"备注","min-width":"110"}}),a("el-table-column",{attrs:{prop:"editorName",label:"最近编辑","min-width":"50"}}),a("el-table-column",{attrs:{prop:"createTime",label:"发布时间","min-width":"100"}}),a("el-table-column",{attrs:{prop:"updateTime",label:"更新时间","min-width":"100"}}),a("el-table-column",{attrs:{label:"操作",fixed:"right","min-width":"180"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("div",{staticClass:"cell-contain"},[[a("el-button",{attrs:{size:"small",type:"primary"},on:{click:function(a){return e.handleEdit(t.row)}}},[e._v("编辑")])],a("el-badge",{staticClass:"handle-div",attrs:{value:t.row.uploadFileCount}},[a("el-button",{attrs:{size:"small",type:"info",disabled:0===t.row.uploadFileCount},on:{click:function(a){return e.handleUpload(t.row)}}},[e._v("查看提交情况")])],1)],2)]}}])})],1)],1)],1),a("el-pagination",{attrs:{background:"",total:e.pageInfo.total,"page-count":e.pageInfo.pages,"current-page":e.pageInfo.current,"hide-on-single-page":""},on:{"current-change":e.currentChange}})],2)],1)},i=[],n=(a("b0c0"),{data:function(){return{workDataList:[],pageInfo:null,tableLoading:!1,multipleSelection:[],selectWorkName:null,selectCreateTime:null,dialogSelectVisible:!1,dialogExtensionVisible:!1,dialogTitle:"",workFormLoading:!1,extensionTableLoading:!1,isExtensionsChange:!1,extensionValidateForm:{extension:""},extensions:[],isEditWork:!1,fileNameFormats:[{enum:1,format:"无"},{enum:2,format:"学号-姓名-作业名"},{enum:3,format:"姓名-学号-作业名"},{enum:4,format:"姓名-作业名"},{enum:5,format:"学号-作业名"}],rules:{name:[{required:!0,message:"请输入作业名称",trigger:"blur"}],lastTime:[{required:!0,message:"请输入截止提交时间",trigger:"blur"}],extensionId:[{required:!0,message:"选择文件拓展名",trigger:"blur"}],fileNameFormatEnum:[{required:!0,message:"请输入文件命名格式",trigger:"blur"}]},WorkLastTimePickerOptions:{disabledDate:function(e){return e.getTime()<=Date.now()}},workData:{id:null,sid:null,fileNameFormatEnum:null,extensionId:null,name:null,lastTime:null,remarks:null,editSId:null},pickerOptions:{disabledDate:function(e){return e.getTime()>Date.now()},shortcuts:[{text:"今天",onClick:function(e){e.$emit("pick",new Date)}},{text:"昨天",onClick:function(e){var t=new Date;t.setTime(t.getTime()-864e5),e.$emit("pick",t)}}]}}},computed:{currentUser:function(){return void 0!==this.$store.state.currentUser.role?this.$store.state.currentUser:JSON.parse(localStorage.getItem("INIT_CURRENTUSER"))}},created:function(){this.getWorksData(1,10),this.getExtension()},methods:{currentChange:function(e){this.getWorksData(e,10)},getWorksData:function(e,t){var a=this;""===this.selectWorkName&&(this.selectWorkName=null),this.getRequest("/work/",{current:e,size:t,name:this.selectWorkName,createTime:this.selectCreateTime}).then((function(e){0===e.code&&(a.pageInfo=e.data,a.workDataList=a.pageInfo.records,a.tableLoading=!1)})).catch((function(e){a.tableLoading=!1,console.log(e)}))},selectWorkByName:function(){this.refreshTableData(1,10)},handleSelectionChange:function(e){this.multipleSelection=e},deleteWorks:function(){var e=[];if(this.multipleSelection.length>0){for(var t=0;t<this.multipleSelection.length;t++)e[t]=this.multipleSelection[t].id;this.deleteWorksidList(e)}},handleEdit:function(e){var t=this;this.showDialog("编辑"),this.isEditWork=!0,this.workFormLoading=!0,this.getRequest("/admin/work/"+e.id).then((function(e){0===e.code&&(t.workData.id=e.data.id,t.workData.fileNameFormatEnum=e.data.fileNameFormatEnum,t.workData.name=e.data.name,t.workData.lastTime=e.data.lastTime,t.workData.remarks=e.data.remarks,t.workData.extensionId=e.data.extensionId,t.workFormLoading=!1)})).catch((function(e){console.log(e),t.workFormLoading=!1}))},deleteWorksidList:function(e){var t=this;this.tableLoading=!0,this.deleteRequest("/admin/work/",{idList:e}).then((function(e){0===e.code&&e.data&&(t.$message.success("删除成功!"),t.refreshTableData(t.pageInfo.current,10))})).catch((function(e){t.tableLoading=!1,t.$message.error(e.msg),console.log(e)}))},doAddWork:function(){var e=this;this.$refs["workDataForm"].validate((function(t){if(!t)return console.log("error submit!!"),!1;e.workFormLoading=!0,e.workData.sid=e.currentUser.id,e.postRequest("/admin/work/",e.workData).then((function(t){0===t.code&&(e.$message.success("发布成功!"),e.workFormLoading=!1,e.dialogSelectVisible=!1,e.refreshTableData(1,10))})).catch((function(t){e.$message.success("发布失败!"),e.workFormLoading=!1,console.log(t)}))}))},refreshTableData:function(e,t){this.tableLoading=!0,this.getWorksData(e,t)},initData:function(){this.workData.id=null,this.workData.sid=null,this.workData.fileNameFormatEnum=null,this.workData.name=null,this.workData.lastTime=null,this.workData.remarks=null,this.workData.editSId=null,this.workData.extensionId=null,this.isEditWork=!1,this.$refs["workDataForm"].resetFields()},showDialog:function(e){this.dialogTitle=e,this.dialogSelectVisible=!0},doEditWork:function(){var e=this;this.$refs["workDataForm"].validate((function(t){if(!t)return console.log("error submit!!"),!1;e.workData.editSId=e.currentUser.id,e.putRequest("/admin/work/",e.workData).then((function(t){0===t.code&&(e.$message.success("编辑成功!"),e.dialogSelectVisible=!1,e.refreshTableData(e.pageInfo.current,10))})).catch((function(t){e.$message.error("编辑失败!"),console.log(t)}))}))},getExtension:function(){var e=this;this.getRequest("/work/extensions").then((function(t){0===t.code&&(e.extensions=t.data)}))},fileNameFormat:function(e,t){return this.fileNameFormats[e.fileNameFormatEnum-1].format},handleUpload:function(e){this.$router.push({path:"/workuploadinfo"}),localStorage.setItem("work",JSON.stringify(e))},deleteExtension:function(e){var t=this;this.extensionTableLoading=!0,this.deleteRequest("/admin/work/extensions",{eId:e.id}).then((function(e){0===e.code&&!0===e.data?(t.$message.success("删除成功！"),t.getExtension(),t.isExtensionsChange=!0):t.$message.error("删除失败！"),t.extensionTableLoading=!1})).catch((function(e){t.$message.error("删除失败！"),t.extensionTableLoading=!1,console.log(e)}))},addExtension:function(){var e=this;this.$refs["extensionValidateForm"].validate((function(t){if(!t)return console.log("error submit!!"),!1;e.extensionTableLoading=!0,e.postRequest("/admin/work/extensions",{name:e.extensionValidateForm.extension}).then((function(t){0===t.code&&!0===t.data?(e.$message.success("添加成功！"),e.getExtension(),e.isExtensionsChange=!0):e.$message.error("添加失败！"),e.extensionTableLoading=!1})).catch((function(t){e.extensionTableLoading=!1,console.log(t)}))}))},whenDialogExtensionClose:function(e){this.$refs[e].resetFields(),this.isExtensionsChange&&(this.refreshTableData(1,10),this.isExtensionsChange=!1)},tableRowClassName:function(e,t){return 1===e.id?"hidden-row":""}}}),l=n,r=(a("de094"),a("2877")),s=Object(r["a"])(l,o,i,!1,null,null,null);t["default"]=s.exports},"786f":function(e,t,a){},de094:function(e,t,a){"use strict";var o=a("786f"),i=a.n(o);i.a}}]);
//# sourceMappingURL=chunk-d9dbc3b2.3db0eba8.js.map