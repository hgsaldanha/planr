package util.base;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class Model implements Serializable {
	
	public abstract boolean equals(Object obj);
}
