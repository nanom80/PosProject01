package posproject.vo;

public class CategoryVO {
	// field
	private int categoryId;
	private String categoryName;
	private String categoryEmoji;
	private String categoryDesc;
	private String categoryStatus;

	// editor
	public CategoryVO() {
		super();
	}

	public CategoryVO(String categoryStatus) {
		super();
		this.categoryStatus = categoryStatus;
	}

	public CategoryVO(int categoryId, String categoryName, String categoryEmoji, String categoryDesc,
			String categoryStatus) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryEmoji = categoryEmoji;
		this.categoryDesc = categoryDesc;
		this.categoryStatus = categoryStatus;
	}

	public CategoryVO(int categoryId, String categoryName, String categoryEmoji, String categoryDesc) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryEmoji = categoryEmoji;
		this.categoryDesc = categoryDesc;
	}

	// method g/s
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryEmoji() {
		return categoryEmoji;
	}

	public void setCategoryEmoji(String categoryEmoji) {
		this.categoryEmoji = categoryEmoji;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	public String getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(String categoryStatus) {
		this.categoryStatus = categoryStatus;
	}
	// method normal
}
