package hadl.m0.descriptionLangage;

import hadl.m1.CS.CS;
import hadl.m2.configuration.Configuration;

public class MainDescriptionLangage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DescriptionLangage test = new DescriptionLangage("src/hadl/m0/descriptionLangage/exempleCS.xml");
		CS testConf = (CS) test.analyse();
		testConf.start();
	}

}
