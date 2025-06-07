<template>
  <div class="goods-detail">
    <!-- 房间轮播图 -->
    <el-carousel :interval="3000" type="card" height="400px" v-if="goodsImages.length">
      <el-carousel-item v-for="(img, index) in goodsImages" :key="index">
        <el-image :src="img" fit="cover" style="width: 100%; height: 100%"/>
      </el-carousel-item>
    </el-carousel>

    <!-- 房间信息 -->
    <el-card class="goods-info-card" shadow="hover">
      <h2>{{ goodsInfo.goodsName }}</h2>
      <p><strong>价格：</strong> ¥{{ goodsInfo.goodsPrice }}</p>
      <p><strong>描述：</strong> {{ goodsInfo.description }}</p>
      <p>
        <strong>
          <el-button type="text" @click="handlePurchase">立即购买</el-button>
        </strong>
      </p>
    </el-card>
    <!-- 添加或修改订房记录对话框 -->
    <el-dialog :title="title" :visible.sync="openPurchase" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="支付凭证" prop="paymentCertificate">
          <image-upload v-model="form.paymentCertificate"/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitPurchaseForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>


import { getGoodsInfo } from '@/api/manage/goodsInfo'
import { addGoodsOrderInfo } from '@/api/manage/goodsOrderInfo'

export default {
  name: 'RoomDetail',
  data() {
    return {
      title: '购买商品',
      openPurchase: false,
      form: {
        goodsId: '',
        userId: '',
        reserveTime: '',
        payTime: '',
        payStatus: '',
        payType: '',
        payAmount: '',
        remark: ''
      },
      baseUrl: process.env.VUE_APP_BASE_API,
      goodsId: 1, // 实际使用中通过路由或父组件传入
      goodsInfo: {
        goodsName: '',
        goodsPrice: 0,
        goodsDesc: '',
        goodsImage: '',
        goodsStatus: 0,
        remark: ''
      },
      goodsImages: [],
      comments: [],
      // 表单校验
      rules: {
        goodsId: [
          { required: true, message: "商品编号不能为空", trigger: "blur" }
        ],
        totalAmount: [
          { required: true, message: "支付金额不能为空", trigger: "blur" }
        ],
        paymentCertificate: [
          { required: true, message: "支付凭证不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "订单状态不能为空", trigger: "change" }
        ],
        userId: [
          { required: true, message: "用户不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
      }
    }
  },

  created() {
    const goodsId = this.$route.params && this.$route.params.goodsId
    this.form.goodsId = goodsId
    this.getGoodsInfo(goodsId)
  },
  methods: {
    //购买
    handlePurchase() {
      this.title = '购买--' + this.goodsInfo.goodsName
      this.openPurchase = true
    },
    //提交购买商品
    submitPurchaseForm() {
      addGoodsOrderInfo(this.form).then(res => {
        this.$modal.msgSuccess('购买成功')
        this.openPurchase = false
        this.getGoodsInfo(this.form.goodsId)
      })
    },
    // 取消按钮
    cancel() {
      this.openPurchase = false
    },
    getGoodsInfo(id) {
      getGoodsInfo(id).then(response => {
        this.goodsInfo = response.data
        this.goodsImages = this.goodsInfo.goodsImage.split(',').map(url => {
          return this.baseUrl + url
        })
      })
    }
  }
}
</script>

<style scoped>
.goods-detail {
  max-width: 1000px;
  margin: 0 auto;
  padding: 20px;
}

.goods-info-card {
  margin-top: 20px;
}

.comment-section {
  margin-top: 40px;
}

.comment-card {
  margin-bottom: 20px;
}

.comment-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 8px;
}

.comment-content {
  margin-bottom: 10px;
}

.comment-images {
  display: flex;
  flex-wrap: wrap;
}
</style>
