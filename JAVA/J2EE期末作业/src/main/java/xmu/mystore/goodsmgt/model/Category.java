package xmu.mystore.goodsmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="142492_category")
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable = false)
	private Long id;	    	 //Ʒ��id
	@Column
	private String name;		 //Ʒ������
	@Column
	private Long upper_category_id;	 //�ϼ�Ʒ��id
	@Column
	private Integer rank;		 //Ʒ��ȼ�
	@Column
	private Integer type;		 //Ʒ��״̬��Ŀǰ��ʾ�Ƿ���ǰ̨��ʾ��
	@Column
	private Integer priority;	         //Ʒ��ǰ̨��ʾ˳��

	public Long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getUpper_category_id() {
		return upper_category_id;
	}
	public void setUpper_category_id(long upper_category_id) {
		this.upper_category_id = upper_category_id;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
}
