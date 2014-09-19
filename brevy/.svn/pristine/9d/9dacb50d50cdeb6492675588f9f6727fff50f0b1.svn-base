package com.brevy.fw.common.entity;

import java.io.Serializable;
import javax.persistence.*;



/**
 * The persistent class for the AP_SEQ database table.
 * 
 */
@Entity
@Table(name="AP_SEQ")
public class ApSeq implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SEQ_NAME")
	private String seqName;

	@Column(name="SEQ_VALUE")
	private long seqValue;

    public ApSeq() {
    }

	public String getSeqName() {
		return this.seqName;
	}

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}

	public long getSeqValue() {
		return this.seqValue;
	}

	public void setSeqValue(long seqValue) {
		this.seqValue = seqValue;
	}

}