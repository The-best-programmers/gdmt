
<template>
<el-container>
  <el-header>
    新增角色信息
  </el-header>
  <el-main>
   <el-form ref="form" :model="form" :rules="formrules" status-icon label-width="80px" >
  <el-form-item label="角色名称" prop="name" style="margin-left:26%">
    <el-input v-model="form.name" maxlength="20" style="width:50%;float:left"
     suffix-icon="el-icon-user-solid"
  show-word-limit></el-input>
  </el-form-item>
  <el-form-item label="关联资源" style="margin-left:26%">
    <el-select v-model="res" multiple clearable placeholder="请选择" style="width:50%;float:left">
    <el-option
    size='medium'
      v-for="item in reslist"
      :key="item.id"
      :label="item.name"
      :value="item.id">
    </el-option>
  </el-select>
  </el-form-item>
    <el-button type="primary" @click="onSubmit('form')">立即创建</el-button>
  </el-form-item>
</el-form>
  </el-main>
</el-container>
</template>

<script>
  export default {
    data() {
      return {
        formrules: {
          name: [{ required: true, message: "不能为空", trigger: "blur" }]
        },
        res:[],
        form: {
          name: '',
          resid:''
        },
        reslist:[]
      }
    },
    mounted(){
      this.getreslist()
    },
    methods: {
      getreslist(){
        this.axios
        .get("http://localhost:8081/Res/list")
        .then(res=>{
          this.reslist=res.data
          //console.log(this.reslist)
        })
      },
      onSubmit(form) {
        this.form.resid=JSON.stringify(this.res)
        //console.log(this.form)
        this.$refs[form].validate(valid=>{
          if(valid){
            this.axios
            .post("http://localhost:8081/Role/insert",this.form)
            .then(res=>{
              this.$message({
                message: '恭喜你，这是一条成功消息',
                type: 'success'
              })
            })
          }
        })
        
      }
    }
  }
</script>
<style>
.el-table td, .el-table th{
  text-align :center !important;
}

.el-header {
  border-radius: 4px;
  border: 1px solid rgba(228, 242, 234, 0.8);
    box-shadow: 0 0 2px rgba(0, 0, 0, 0.2);
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
    padding: 0;
    width: 97%;
    margin-left: 1.5%;
  }
  .el-main{
    width:98.5%;
    padding: 10px;
   text-align: center;
   margin-left: 0.9%;
  }
 .orgselect{
   float:left;
 }
</style>