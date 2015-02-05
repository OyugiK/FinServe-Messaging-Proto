package com.oyugik.finserve.iso8583.utility;

import java.io.IOException;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;
 
public class ParseISOMessage {
 
	public static void main(String[] args) throws IOException, ISOException {
		// Create Packager based on XML that contain DE type
		GenericPackager packager = new GenericPackager("basic.xml");
 
		// Print Input Data
		String data = "0210723A00010A80840018593600141001099999011000000010000000100702153300000119153310061007065656561006090102240000000901360020100236C0102240000000";
		System.out.println("DATA : " + data);
 
		// Create ISO Message
		ISOMsg isoMsg = new ISOMsg();
		isoMsg.setPackager(packager);
		isoMsg.unpack(data.getBytes());
 
		// print the DE list
		logISOMsg(isoMsg);
	}
 
	private static void logISOMsg(ISOMsg msg) {
		System.out.println("----ISO MESSAGE-----");
		try {
			System.out.println("  MTI : " + msg.getMTI());
			for (int i=1;i<msg.getMaxField();i++) {
				if (msg.hasField(i)) {
					System.out.println("    Field-"+i+" : "+msg.getString(i));
				}
			}
		} catch (ISOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("--------------------");
		}
 
	}
 
}
