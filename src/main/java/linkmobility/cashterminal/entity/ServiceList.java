package linkmobility.cashterminal.entity;

import java.util.ArrayList;
import java.util.List;

public class ServiceList {
	
	    private List<Service> serviceList;

	    public ServiceList() {
	    	
	    	this.serviceList = new ArrayList<Service>();
	    }

		public List<Service> getServiceList() {
			return serviceList;
		}

		public void setServiceList(List<Service> serviceList) {
			this.serviceList = serviceList;
		}	
}
