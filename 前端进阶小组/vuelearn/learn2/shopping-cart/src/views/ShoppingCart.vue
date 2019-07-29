<template>
    <div style="width: 1100px;margin: auto;align-content: center">
        <Row type="flex" justify="end">
            <Button @click="showAddModal = true">添加商品</Button>
        </Row>
        <Row type="flex" justify="center">
            <Table :width="1100" :columns="cartColumns" :data="cartData"/>
        </Row>
        <Row type="flex" justify="start">
            <p>总价格:￥{{totalPrice}}</p>
        </Row>

        <Modal title="添加商品" v-model="showAddModal" @on-ok="addProduct">
                <Form :label-width='100'>
                    <form-item label="商品名称">
                        <i-Input style="width: 200px" v-model="addData.pName"></i-Input>
                    </form-item>
                    <form-item label="商品价格">
                        <Input-Number style="width: 200px" v-model="addData.price"></Input-Number>
                    </form-item>
                    <form-item label="商品数量">
                        <Input-Number  style="width: 200px" v-model="addData.num"></Input-Number>
                    </form-item>
                </Form>
        </Modal>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                showAddModal: false,
                addData: {
                    pName: '',
                    price: 0,
                    num: 0
                },
                cartData: [
                    {
                        pName: '苹果X MAX',
                        price: 5000,
                        num: 2
                    },
                    {
                        pName: '华为Mate20',
                        num: 3,
                        price: 9999
                    },
                    {
                        pName: '小米MIX',
                        num: 1,
                        price: 2000
                    }
                ],
                cartColumns: [
                    {
                        title: '序号',
                        key: 'index',
                        type: 'index'
                    },
                    {
                        title: '商品名称',
                        key: 'pName'
                    },
                    {
                        title: '商品价格',
                        key: 'price'
                    },
                    {
                        title: '购买数量',
                        key: 'num',
                        render: (h, param) => {
                            let arr = []
                            arr.push(h('Button', {
                                props: {
                                    icon: 'md-remove'
                                },
                                on: {
                                    'click': () => {
                                        if (this.cartData[param.index].num > 0) {
                                            this.cartData[param.index].num--
                                        }
                                    }
                                }
                            }))
                            arr.push(h('InputNumber', {
                                style: {
                                    paddingLeft: '5px',
                                    paddingRight: '5px',
                                    width: '55px'
                                },
                                props: {
                                    value: param.row.num
                                },
                                on: {
                                    'on-change': (value) => {
                                        param.row.num = value
                                        this.cartData[param.index].num = value
                                    }
                                }
                            }))
                            arr.push(h('Button', {
                                props: {
                                    icon: 'md-add'
                                },
                                on: {
                                    'click': () => {
                                        this.cartData[param.index].num++
                                    }
                                }
                            }))
                            return h('div', arr)
                        }
                    },
                    {
                        title: '操作',
                        key: '',
                        render: (h, param) => {
                            return h('Button', {
                                props: {
                                    type: 'error'
                                },
                                on: {
                                    'click': () => {
                                        this.cartData.splice(param.index, 1)
                                    }
                                }
                            }, '删除')
                        }
                    },
                ]
            };
        },
        mounted() {

        },
        computed: {
            totalPrice(){
                if (this.cartData && this.cartData.length > 0) {
                    return this.cartData.map(p => {
                        if (p.num > 0) {
                            return p.num * p.price
                        } else {
                            return 0
                        }
                    }).reduce((p1, p2) => p1 + p2)
                }
                return 0
            }
        },
        beforeDestroy() {

        },
        methods: {
            addProduct(){
                this.cartData.push(Object.assign({},this.addData))
            }
        }
    };
</script>

<style>

</style>