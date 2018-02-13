/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package davisbase;

/**
 *
 * @author VYOMA
 */
public class DataNode {


	private long data;
	private String value;

	DataNode() {
		data = 0;
		value = "";
	}

	@Override
	public String toString() {
		return String.valueOf(data) + value;
	}

	public DataNode(long x, String value) {
		this.data = x;
		this.value = value;
	}

	public long getData() {
		return data;
	}

	public void setData(long data) {
		this.data = data;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean inOrder(DataNode dnode) {
		return (dnode.getData() <= this.data);
	}
}
