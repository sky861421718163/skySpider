package com.sky.spider.advance.solr;

import org.apache.solr.common.SolrInputDocument;

public class SolrTest {

	public static void main(String[] args) {
		SolrInputDocument input = new SolrInputDocument();
		input.addField("id", "0");
		input.addField("haha", "李");
		boolean f =SolrUtil.pushDataIntoSolr("new_core", input);
		System.out.println(f);
		

		/*boolean f2 = SolrUtil.deleteDataIntoSolrById("new_core", 10);
		System.out.println(f2);*/
	}

}
