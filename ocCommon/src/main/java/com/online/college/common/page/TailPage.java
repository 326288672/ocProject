package com.online.college.common.page;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 分页
 */
public class TailPage<E> extends AbstractPage<E> {
	/**
	 * 显示10个页码
	 */
    protected int showPage = 10;
	protected List<Integer> showNums = new ArrayList();
	protected boolean showDot = true;
	public TailPage() {}
	
    /**
     * 构造函数，将一个已有的分页对象中的分页参数，设置给自己，items需独立设置
     * @param page
     * @param items
     */
    public TailPage(Page<E> page, Collection<E> items ,int itemsTotalCount) {
        this(page.getPageNum(), page.getPageSize(), itemsTotalCount , items);
    }

    public TailPage(int pageNum, int pageSize , int itemsTotalCount , Collection<E> items) {
    	this.setItemsTotalCount(itemsTotalCount);
        this.setPageNum(pageNum);
        this.setPageSize(pageSize);
        this.setItems(items);
        this.initShowNum();
    }

	@Override
	public void setItemsTotalCount(int itemsTotalCount) {
		super.setItemsTotalCount(itemsTotalCount);
		initShowNum();
	}
	
	private void initShowNum(){
		int startIndex;
		int endIndex;
		if(pageNum - showPage/2 > 1){
			startIndex = pageNum-showPage/2;
			endIndex = pageNum + showPage/2 - 1;
			if(endIndex > pageTotalCount){
				endIndex = pageTotalCount;
				startIndex = endIndex - showPage + 1;
			}
		}else{
			startIndex = 1;
			endIndex = pageTotalCount<=showPage?pageTotalCount:showPage;
		}
		for(int i = startIndex; i <= endIndex ; i++){
			this.showNums.add(Integer.valueOf(i));
		}
		if(this.firstPage||this.lastPage){
			showDot = false;
		}else{
			if(showNums.size() > 0){
				if(showNums.get(showNums.size()-1) == this.pageTotalCount){
					showDot = false;
				}
			}
		}
	}

	@Override
	public int getPageTotalCount(){
		return this.pageTotalCount;
	}
}

