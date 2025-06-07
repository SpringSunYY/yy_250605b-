<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <h1 style="font-size: 36px;text-align: center">网上日用百货商品商城管理系统</h1>
      <el-row :gutter="20">
        <el-col :xs="24" :sm="4" :lg="4" v-for="(item,index) in goodsInfoList" :key="index">
          <el-card class="box-card" shadow="hover">
            <image-preview :src="item.goodsImage" :width="200" :height="200"/>
            <div>
              <el-row :gutter="20">
                <el-col :xs="24" :sm="24" :lg="24">
                  <div>
                    <span style="font-size: 16px;">{{ item.goodsName }}</span>
                  </div>
                </el-col>
                <el-col :xs="24" :sm="24" :lg="24">
                  <el-button type="text" @click="toGoodsDetail(item)">查看详情</el-button>
                </el-col>
              </el-row>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </el-row>
  </div>
</template>

<script>
import { listGoodsInfo } from '@/api/manage/goodsInfo'

export default {
  name: 'Index',
  data() {
    return {
      // 版本号
      version: '3.8.9',
      goodsInfoList: [],
      queryParams: {
        status: '1'
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      listGoodsInfo(this.queryParams).then(response => {
        this.goodsInfoList = response.rows
      })
    },
    toGoodsDetail(row) {
      this.$router.push({ path: '/manage/goodsDetail/index/' + row.goodsId })
    },
    goTarget(href) {
      window.open(href, '_blank')
    }
  }
}
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }

  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }

  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>

