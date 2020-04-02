package util.page

/**
 * @Description: 分页工具类
 * @Author: zhangyan
 * @CreateDate:  2020/3/19 15:03
 */

/**
 * @param datas 数据
 * @param perPage 每页条数
 */
class PageHelper<T>(datas:List<T>,perPage: Int) {

    /**
     * 所有数据
     */
    private var allData : List<T>? = null
    /**
     * 每页的条数
     */
    private var perPage = 10
    /**
     * 当前页
     */
    private var currentPage = 1

    /**
     * 页码
     */
    private var pageNum = 1

    /**
     * 每页的子数据
     */
    private var childData : List<T>? = null

    /**
     * 总页数
     */
    private var totalNum = 0

    init {
        this.allData = datas
        if (perPage > 0){
            this.perPage = perPage
        }
        //如果数据大于每页的条数 截取子数据
        if (allData != null && allData?.size!! > perPage){
            childData = allData?.subList(0,perPage - 1)
        }
        //总页数
        totalNum = allData?.size!!

        //如果总数能除断，那么页数就是余数，否则+1
        pageNum = if (totalNum % perPage == 0) (totalNum / perPage) else (totalNum / perPage + 1)
    }

    /**
     * 获取总页数
     */
     fun getCount() : Int{
        return this.totalNum
    }

    /**
     * 获取当前页
     */
     fun getCurrentPage() : Int{
        return this.currentPage
    }

    /**
     * 返回页码
     */
     fun getPageNum() : Int{
        return this.pageNum
    }

    /**
     * 是否有下一页
     */
     fun hasNextPage(): Boolean{
        return currentPage < pageNum
    }

    /**
     * 是否有前一页
     */
     fun hasPrePage(): Boolean{
        return currentPage > 1
    }

    /**
     * 第一页
     */
     fun headPage(){
        currentPage = 1
    }

    /**
     * 最后一页
     */
     fun lastPage(){
        currentPage = pageNum
    }

    /**
     * 下一页
     */
     fun nextPage(){
        currentPage = if (hasNextPage()) currentPage + 1 else pageNum
    }

    /**
     * 前一页
     */
     fun prePage(){
        currentPage = if (hasPrePage()) currentPage - 1 else 1
    }

    /**
     * 设置上一页
     */
     fun setPrePage(perPage: Int){
        this.perPage = perPage
    }

    /**
     * 页面跳转
     */
     fun gotoPage(n: Int){
      currentPage = if (n > pageNum) pageNum else (if (n<1) 1 else n)
    }

    /**
     * 设置新数据
     */
    fun setNewData(datas:List<T>){
        this.allData = datas

        //如果数据大于每页的条数 截取子数据
        if (allData != null && allData?.size!! > perPage){
            childData = allData?.subList(0,perPage - 1)
        }
        //总页数
        totalNum = allData?.size!!

        //如果总数能除断，那么页数就是余数，否则+1
        pageNum = if (totalNum % perPage == 0) (totalNum / perPage) else (totalNum / perPage + 1)
    }

    /**
     * 获取当前数据
     */
     fun getCurrentList(): List<T>{
        //第一页
        if (currentPage == 1){
            if(allData!!.size < perPage){
                childData = allData
            }else {
                childData = allData?.subList(0, perPage)
            }
        }else if (currentPage == pageNum){
            //最后一页
            childData = allData?.subList(perPage * (pageNum - 1),totalNum)
        }else{
            //中间页
            childData = allData?.subList(perPage * (currentPage - 1),perPage * currentPage)
        }

        return this.childData!!
    }

    /**
     * 设置当前页
     */
     fun setCurrentPage(currentPage: Int){
        this.currentPage = currentPage
    }


}