package in.nit.util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class EmailUtil {
	@Autowired
	private JavaMailSender sender;
	
	public boolean sendEmail(
			String to,
			String subject,
			String cc[],
			String bcc[],
			String text,
			MultipartFile file
			) 
	{
		
		boolean sent=false;
		try {
			//1. create MimeMessage (Email Object)
			MimeMessage message=sender.createMimeMessage();
			
			//2. Use Helper Object for MimeMessage
			MimeMessageHelper helper=new MimeMessageHelper(message, file!=null?true:false);
			
			//3. add Message Inputs
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text,true);
			
			if(cc!=null && cc.length>0)
				helper.setCc(cc);
			if(bcc!=null && bcc.length>0)
				helper.setBcc(bcc);
			
			//4. add Attachment
			if(file!=null) {
				//fileName,file
				helper.addAttachment(file.getOriginalFilename(), file);
			}
			
			//5. send message
			sender.send(message);
			
			sent=true;
		} catch (Exception e) {
			sent=false;
			e.printStackTrace();
		}
		
		return sent;
	}
	
	//overloaded methods
	public boolean sendEmail(
			String to,
			String subject,
			String text) 
	{
		return sendEmail(to, subject, null, null, text, null);
	}
	
	public boolean sendEmail(
			String to,
			String subject,
			String text,
			MultipartFile file) 
	{
		return sendEmail(to, subject, null, null, text, file);
	}
	
}




