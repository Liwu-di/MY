package Jdxz.views;

import Jdxz.main.JdxzMain;
import uk.co.caprica.vlcj.binding.internal.libvlc_logo_position_e;
import uk.co.caprica.vlcj.player.Logo;


public class JdxzLogo {
	private Logo logo = Logo.logo()
			.file("/HomeImg.jpg")
			.position(libvlc_logo_position_e.top_left)
			.opacity(0.2f)
			.enable();

	public Logo getLogo() {
		
		return logo;
	}
	
	
	
}
