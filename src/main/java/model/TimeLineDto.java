package model;

import java.sql.Timestamp;

/**----------------------------------------------------------------------*
 *■■■TimeLineDtoクラス■■■
 *概要：DTO（「survey」テーブル）
 *----------------------------------------------------------------------**/
public class TimeLineDto {

	//----------------------------------------------------------------
	//フィールド
	//----------------------------------------------------------------
	private String    name ;                //名前
	private String    task ;                //タスク
	private String    state ;               //状態
	private Timestamp time ;                //更新時刻

	//----------------------------------------------------------------
	//getter/setter
	//----------------------------------------------------------------

	//getter/setter（対象フィールド：name）
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	//getter/setter（対象フィールド：task）
	public String getTask() { return task; }
	public void setTask(String task) { this.task = task; }
	
	//getter/setter（対象フィールド：state）
	public String getState() { return state; }
	public void setState(String state) { this.state = state; }
	
	//getter/setter（対象フィールド：time）
	public Timestamp getTime() { return time; }
	public void setTime(Timestamp time) { this.time = time; }

}
