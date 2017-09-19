package app.services;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private static final String FROM = "reservas@pupi.com.ar";

    public static int sendEmail(String to, String subject) {

        try {
        BasicAWSCredentials credentials = new BasicAWSCredentials("AKIAJC5QINGQL2PXT7TA", "//PQfIw02psEx2ZWmdgCR1KktRJRR5m2qUTwDW+8");
        AmazonSimpleEmailServiceClientBuilder builder = AmazonSimpleEmailServiceClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1.getName())
                .withClientConfiguration(
                        new ClientConfiguration()
                                .withConnectionTimeout(3000)
                                .withRequestTimeout(3000)
                );

        AmazonSimpleEmailService service = builder.build();
        SendEmailRequest sendEmailRequest = createEmailRequest(FROM, to, "Holis", subject);
        SendEmailResult sendEmailResult = service.sendEmail(sendEmailRequest);
        return sendEmailResult.getSdkHttpMetadata().getHttpStatusCode();
        }
        catch(Exception e) {
            throw new AmazonSimpleEmailServiceException(e.toString());
        }
    }

    private static SendEmailRequest createEmailRequest(String from, String to, String text, String subject) {

        String emailTemplate = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><!--[if IE]><html xmlns=\"http://www.w3.org/1999/xhtml\" class=\"ie\"><![endif]--><!--[if !IE]><!--><html style=\"margin: 0;padding: 0;\" xmlns=\"http://www.w3.org/1999/xhtml\"><!--<![endif]--><head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "    <title></title>\n" +
                "    <!--[if !mso]><!--><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" /><!--<![endif]-->\n" +
                "    <meta name=\"viewport\" content=\"width=device-width\" /><style type=\"text/css\">\n" +
                "@media only screen and (min-width: 620px){.wrapper{min-width:600px !important}.wrapper h1{}.wrapper h1{font-size:64px !important;line-height:63px !important}.wrapper h2{}.wrapper h2{font-size:30px !important;line-height:38px !important}.wrapper h3{}.wrapper h3{font-size:22px !important;line-height:31px !important}.column{}.wrapper .size-8{font-size:8px !important;line-height:14px !important}.wrapper .size-9{font-size:9px !important;line-height:16px !important}.wrapper .size-10{font-size:10px !important;line-height:18px !important}.wrapper .size-11{font-size:11px !important;line-height:19px !important}.wrapper .size-12{font-size:12px !important;line-height:19px !important}.wrapper .size-13{font-size:13px !important;line-height:21px !important}.wrapper .size-14{font-size:14px !important;line-height:21px !important}.wrapper .size-15{font-size:15px !important;line-height:23px \n" +
                "!important}.wrapper .size-16{font-size:16px !important;line-height:24px !important}.wrapper .size-17{font-size:17px !important;line-height:26px !important}.wrapper .size-18{font-size:18px !important;line-height:26px !important}.wrapper .size-20{font-size:20px !important;line-height:28px !important}.wrapper .size-22{font-size:22px !important;line-height:31px !important}.wrapper .size-24{font-size:24px !important;line-height:32px !important}.wrapper .size-26{font-size:26px !important;line-height:34px !important}.wrapper .size-28{font-size:28px !important;line-height:36px !important}.wrapper .size-30{font-size:30px !important;line-height:38px !important}.wrapper .size-32{font-size:32px !important;line-height:40px !important}.wrapper .size-34{font-size:34px !important;line-height:43px !important}.wrapper .size-36{font-size:36px !important;line-height:43px !important}.wrapper \n" +
                ".size-40{font-size:40px !important;line-height:47px !important}.wrapper .size-44{font-size:44px !important;line-height:50px !important}.wrapper .size-48{font-size:48px !important;line-height:54px !important}.wrapper .size-56{font-size:56px !important;line-height:60px !important}.wrapper .size-64{font-size:64px !important;line-height:63px !important}}\n" +
                "</style>\n" +
                "    <style type=\"text/css\">\n" +
                "body {\n" +
                "  margin: 0;\n" +
                "  padding: 0;\n" +
                "}\n" +
                "table {\n" +
                "  border-collapse: collapse;\n" +
                "  table-layout: fixed;\n" +
                "}\n" +
                "* {\n" +
                "  line-height: inherit;\n" +
                "}\n" +
                "[x-apple-data-detectors],\n" +
                "[href^=\"tel\"],\n" +
                "[href^=\"sms\"] {\n" +
                "  color: inherit !important;\n" +
                "  text-decoration: none !important;\n" +
                "}\n" +
                ".wrapper .footer__share-button a:hover,\n" +
                ".wrapper .footer__share-button a:focus {\n" +
                "  color: #ffffff !important;\n" +
                "}\n" +
                ".btn a:hover,\n" +
                ".btn a:focus,\n" +
                ".footer__share-button a:hover,\n" +
                ".footer__share-button a:focus,\n" +
                ".email-footer__links a:hover,\n" +
                ".email-footer__links a:focus {\n" +
                "  opacity: 0.8;\n" +
                "}\n" +
                ".preheader,\n" +
                ".header,\n" +
                ".layout,\n" +
                ".column {\n" +
                "  transition: width 0.25s ease-in-out, max-width 0.25s ease-in-out;\n" +
                "}\n" +
                ".layout,\n" +
                "div.header {\n" +
                "  max-width: 400px !important;\n" +
                "  -fallback-width: 95% !important;\n" +
                "  width: calc(100% - 20px) !important;\n" +
                "}\n" +
                "div.preheader {\n" +
                "  max-width: 360px !important;\n" +
                "  -fallback-width: 90% !important;\n" +
                "  width: calc(100% - 60px) !important;\n" +
                "}\n" +
                ".snippet,\n" +
                ".webversion {\n" +
                "  Float: none !important;\n" +
                "}\n" +
                ".column {\n" +
                "  max-width: 400px !important;\n" +
                "  width: 100% !important;\n" +
                "}\n" +
                ".fixed-width.has-border {\n" +
                "  max-width: 402px !important;\n" +
                "}\n" +
                ".fixed-width.has-border .layout__inner {\n" +
                "  box-sizing: border-box;\n" +
                "}\n" +
                ".snippet,\n" +
                ".webversion {\n" +
                "  width: 50% !important;\n" +
                "}\n" +
                ".ie .btn {\n" +
                "  width: 100%;\n" +
                "}\n" +
                "[owa] .column div,\n" +
                "[owa] .column button {\n" +
                "  display: block !important;\n" +
                "}\n" +
                ".ie .column,\n" +
                "[owa] .column,\n" +
                ".ie .gutter,\n" +
                "[owa] .gutter {\n" +
                "  display: table-cell;\n" +
                "  float: none !important;\n" +
                "  vertical-align: top;\n" +
                "}\n" +
                ".ie div.preheader,\n" +
                "[owa] div.preheader,\n" +
                ".ie .email-footer,\n" +
                "[owa] .email-footer {\n" +
                "  max-width: 560px !important;\n" +
                "  width: 560px !important;\n" +
                "}\n" +
                ".ie .snippet,\n" +
                "[owa] .snippet,\n" +
                ".ie .webversion,\n" +
                "[owa] .webversion {\n" +
                "  width: 280px !important;\n" +
                "}\n" +
                ".ie div.header,\n" +
                "[owa] div.header,\n" +
                ".ie .layout,\n" +
                "[owa] .layout,\n" +
                ".ie .one-col .column,\n" +
                "[owa] .one-col .column {\n" +
                "  max-width: 600px !important;\n" +
                "  width: 600px !important;\n" +
                "}\n" +
                ".ie .fixed-width.has-border,\n" +
                "[owa] .fixed-width.has-border,\n" +
                ".ie .has-gutter.has-border,\n" +
                "[owa] .has-gutter.has-border {\n" +
                "  max-width: 602px !important;\n" +
                "  width: 602px !important;\n" +
                "}\n" +
                ".ie .two-col .column,\n" +
                "[owa] .two-col .column {\n" +
                "  max-width: 300px !important;\n" +
                "  width: 300px !important;\n" +
                "}\n" +
                ".ie .three-col .column,\n" +
                "[owa] .three-col .column,\n" +
                ".ie .narrow,\n" +
                "[owa] .narrow {\n" +
                "  max-width: 200px !important;\n" +
                "  width: 200px !important;\n" +
                "}\n" +
                ".ie .wide,\n" +
                "[owa] .wide {\n" +
                "  width: 400px !important;\n" +
                "}\n" +
                ".ie .two-col.has-gutter .column,\n" +
                "[owa] .two-col.x_has-gutter .column {\n" +
                "  max-width: 290px !important;\n" +
                "  width: 290px !important;\n" +
                "}\n" +
                ".ie .three-col.has-gutter .column,\n" +
                "[owa] .three-col.x_has-gutter .column,\n" +
                ".ie .has-gutter .narrow,\n" +
                "[owa] .has-gutter .narrow {\n" +
                "  max-width: 188px !important;\n" +
                "  width: 188px !important;\n" +
                "}\n" +
                ".ie .has-gutter .wide,\n" +
                "[owa] .has-gutter .wide {\n" +
                "  max-width: 394px !important;\n" +
                "  width: 394px !important;\n" +
                "}\n" +
                ".ie .two-col.has-gutter.has-border .column,\n" +
                "[owa] .two-col.x_has-gutter.x_has-border .column {\n" +
                "  max-width: 292px !important;\n" +
                "  width: 292px !important;\n" +
                "}\n" +
                ".ie .three-col.has-gutter.has-border .column,\n" +
                "[owa] .three-col.x_has-gutter.x_has-border .column,\n" +
                ".ie .has-gutter.has-border .narrow,\n" +
                "[owa] .has-gutter.x_has-border .narrow {\n" +
                "  max-width: 190px !important;\n" +
                "  width: 190px !important;\n" +
                "}\n" +
                ".ie .has-gutter.has-border .wide,\n" +
                "[owa] .has-gutter.x_has-border .wide {\n" +
                "  max-width: 396px !important;\n" +
                "  width: 396px !important;\n" +
                "}\n" +
                ".ie .fixed-width .layout__inner {\n" +
                "  border-left: 0 none white !important;\n" +
                "  border-right: 0 none white !important;\n" +
                "}\n" +
                ".ie .layout__edges {\n" +
                "  display: none;\n" +
                "}\n" +
                ".mso .layout__edges {\n" +
                "  font-size: 0;\n" +
                "}\n" +
                ".layout-fixed-width,\n" +
                ".mso .layout-full-width {\n" +
                "  background-color: #ffffff;\n" +
                "}\n" +
                "@media only screen and (min-width: 620px) {\n" +
                "  .column,\n" +
                "  .gutter {\n" +
                "    display: table-cell;\n" +
                "    Float: none !important;\n" +
                "    vertical-align: top;\n" +
                "  }\n" +
                "  div.preheader,\n" +
                "  .email-footer {\n" +
                "    max-width: 560px !important;\n" +
                "    width: 560px !important;\n" +
                "  }\n" +
                "  .snippet,\n" +
                "  .webversion {\n" +
                "    width: 280px !important;\n" +
                "  }\n" +
                "  div.header,\n" +
                "  .layout,\n" +
                "  .one-col .column {\n" +
                "    max-width: 600px !important;\n" +
                "    width: 600px !important;\n" +
                "  }\n" +
                "  .fixed-width.has-border,\n" +
                "  .fixed-width.ecxhas-border,\n" +
                "  .has-gutter.has-border,\n" +
                "  .has-gutter.ecxhas-border {\n" +
                "    max-width: 602px !important;\n" +
                "    width: 602px !important;\n" +
                "  }\n" +
                "  .two-col .column {\n" +
                "    max-width: 300px !important;\n" +
                "    width: 300px !important;\n" +
                "  }\n" +
                "  .three-col .column,\n" +
                "  .column.narrow {\n" +
                "    max-width: 200px !important;\n" +
                "    width: 200px !important;\n" +
                "  }\n" +
                "  .column.wide {\n" +
                "    width: 400px !important;\n" +
                "  }\n" +
                "  .two-col.has-gutter .column,\n" +
                "  .two-col.ecxhas-gutter .column {\n" +
                "    max-width: 290px !important;\n" +
                "    width: 290px !important;\n" +
                "  }\n" +
                "  .three-col.has-gutter .column,\n" +
                "  .three-col.ecxhas-gutter .column,\n" +
                "  .has-gutter .narrow {\n" +
                "    max-width: 188px !important;\n" +
                "    width: 188px !important;\n" +
                "  }\n" +
                "  .has-gutter .wide {\n" +
                "    max-width: 394px !important;\n" +
                "    width: 394px !important;\n" +
                "  }\n" +
                "  .two-col.has-gutter.has-border .column,\n" +
                "  .two-col.ecxhas-gutter.ecxhas-border .column {\n" +
                "    max-width: 292px !important;\n" +
                "    width: 292px !important;\n" +
                "  }\n" +
                "  .three-col.has-gutter.has-border .column,\n" +
                "  .three-col.ecxhas-gutter.ecxhas-border .column,\n" +
                "  .has-gutter.has-border .narrow,\n" +
                "  .has-gutter.ecxhas-border .narrow {\n" +
                "    max-width: 190px !important;\n" +
                "    width: 190px !important;\n" +
                "  }\n" +
                "  .has-gutter.has-border .wide,\n" +
                "  .has-gutter.ecxhas-border .wide {\n" +
                "    max-width: 396px !important;\n" +
                "    width: 396px !important;\n" +
                "  }\n" +
                "}\n" +
                "@media only screen and (-webkit-min-device-pixel-ratio: 2), only screen and (min--moz-device-pixel-ratio: 2), only screen and (-o-min-device-pixel-ratio: 2/1), only screen and (min-device-pixel-ratio: 2), only screen and (min-resolution: 192dpi), only screen and (min-resolution: 2dppx) {\n" +
                "  .fblike {\n" +
                "    background-image: url(https://i7.createsend1.com/static/eb/master/13-the-blueprint-3/images/fblike@2x.png) !important;\n" +
                "  }\n" +
                "  .tweet {\n" +
                "    background-image: url(https://i8.createsend1.com/static/eb/master/13-the-blueprint-3/images/tweet@2x.png) !important;\n" +
                "  }\n" +
                "  .linkedinshare {\n" +
                "    background-image: url(https://i9.createsend1.com/static/eb/master/13-the-blueprint-3/images/lishare@2x.png) !important;\n" +
                "  }\n" +
                "  .forwardtoafriend {\n" +
                "    background-image: url(https://i10.createsend1.com/static/eb/master/13-the-blueprint-3/images/forward@2x.png) !important;\n" +
                "  }\n" +
                "}\n" +
                "@media (max-width: 321px) {\n" +
                "  .fixed-width.has-border .layout__inner {\n" +
                "    border-width: 1px 0 !important;\n" +
                "  }\n" +
                "  .layout,\n" +
                "  .column {\n" +
                "    min-width: 320px !important;\n" +
                "    width: 320px !important;\n" +
                "  }\n" +
                "  .border {\n" +
                "    display: none;\n" +
                "  }\n" +
                "}\n" +
                ".mso div {\n" +
                "  border: 0 none white !important;\n" +
                "}\n" +
                ".mso .w560 .divider {\n" +
                "  Margin-left: 260px !important;\n" +
                "  Margin-right: 260px !important;\n" +
                "}\n" +
                ".mso .w360 .divider {\n" +
                "  Margin-left: 160px !important;\n" +
                "  Margin-right: 160px !important;\n" +
                "}\n" +
                ".mso .w260 .divider {\n" +
                "  Margin-left: 110px !important;\n" +
                "  Margin-right: 110px !important;\n" +
                "}\n" +
                ".mso .w160 .divider {\n" +
                "  Margin-left: 60px !important;\n" +
                "  Margin-right: 60px !important;\n" +
                "}\n" +
                ".mso .w354 .divider {\n" +
                "  Margin-left: 157px !important;\n" +
                "  Margin-right: 157px !important;\n" +
                "}\n" +
                ".mso .w250 .divider {\n" +
                "  Margin-left: 105px !important;\n" +
                "  Margin-right: 105px !important;\n" +
                "}\n" +
                ".mso .w148 .divider {\n" +
                "  Margin-left: 54px !important;\n" +
                "  Margin-right: 54px !important;\n" +
                "}\n" +
                ".mso .size-8,\n" +
                ".ie .size-8 {\n" +
                "  font-size: 8px !important;\n" +
                "  line-height: 14px !important;\n" +
                "}\n" +
                ".mso .size-9,\n" +
                ".ie .size-9 {\n" +
                "  font-size: 9px !important;\n" +
                "  line-height: 16px !important;\n" +
                "}\n" +
                ".mso .size-10,\n" +
                ".ie .size-10 {\n" +
                "  font-size: 10px !important;\n" +
                "  line-height: 18px !important;\n" +
                "}\n" +
                ".mso .size-11,\n" +
                ".ie .size-11 {\n" +
                "  font-size: 11px !important;\n" +
                "  line-height: 19px !important;\n" +
                "}\n" +
                ".mso .size-12,\n" +
                ".ie .size-12 {\n" +
                "  font-size: 12px !important;\n" +
                "  line-height: 19px !important;\n" +
                "}\n" +
                ".mso .size-13,\n" +
                ".ie .size-13 {\n" +
                "  font-size: 13px !important;\n" +
                "  line-height: 21px !important;\n" +
                "}\n" +
                ".mso .size-14,\n" +
                ".ie .size-14 {\n" +
                "  font-size: 14px !important;\n" +
                "  line-height: 21px !important;\n" +
                "}\n" +
                ".mso .size-15,\n" +
                ".ie .size-15 {\n" +
                "  font-size: 15px !important;\n" +
                "  line-height: 23px !important;\n" +
                "}\n" +
                ".mso .size-16,\n" +
                ".ie .size-16 {\n" +
                "  font-size: 16px !important;\n" +
                "  line-height: 24px !important;\n" +
                "}\n" +
                ".mso .size-17,\n" +
                ".ie .size-17 {\n" +
                "  font-size: 17px !important;\n" +
                "  line-height: 26px !important;\n" +
                "}\n" +
                ".mso .size-18,\n" +
                ".ie .size-18 {\n" +
                "  font-size: 18px !important;\n" +
                "  line-height: 26px !important;\n" +
                "}\n" +
                ".mso .size-20,\n" +
                ".ie .size-20 {\n" +
                "  font-size: 20px !important;\n" +
                "  line-height: 28px !important;\n" +
                "}\n" +
                ".mso .size-22,\n" +
                ".ie .size-22 {\n" +
                "  font-size: 22px !important;\n" +
                "  line-height: 31px !important;\n" +
                "}\n" +
                ".mso .size-24,\n" +
                ".ie .size-24 {\n" +
                "  font-size: 24px !important;\n" +
                "  line-height: 32px !important;\n" +
                "}\n" +
                ".mso .size-26,\n" +
                ".ie .size-26 {\n" +
                "  font-size: 26px !important;\n" +
                "  line-height: 34px !important;\n" +
                "}\n" +
                ".mso .size-28,\n" +
                ".ie .size-28 {\n" +
                "  font-size: 28px !important;\n" +
                "  line-height: 36px !important;\n" +
                "}\n" +
                ".mso .size-30,\n" +
                ".ie .size-30 {\n" +
                "  font-size: 30px !important;\n" +
                "  line-height: 38px !important;\n" +
                "}\n" +
                ".mso .size-32,\n" +
                ".ie .size-32 {\n" +
                "  font-size: 32px !important;\n" +
                "  line-height: 40px !important;\n" +
                "}\n" +
                ".mso .size-34,\n" +
                ".ie .size-34 {\n" +
                "  font-size: 34px !important;\n" +
                "  line-height: 43px !important;\n" +
                "}\n" +
                ".mso .size-36,\n" +
                ".ie .size-36 {\n" +
                "  font-size: 36px !important;\n" +
                "  line-height: 43px !important;\n" +
                "}\n" +
                ".mso .size-40,\n" +
                ".ie .size-40 {\n" +
                "  font-size: 40px !important;\n" +
                "  line-height: 47px !important;\n" +
                "}\n" +
                ".mso .size-44,\n" +
                ".ie .size-44 {\n" +
                "  font-size: 44px !important;\n" +
                "  line-height: 50px !important;\n" +
                "}\n" +
                ".mso .size-48,\n" +
                ".ie .size-48 {\n" +
                "  font-size: 48px !important;\n" +
                "  line-height: 54px !important;\n" +
                "}\n" +
                ".mso .size-56,\n" +
                ".ie .size-56 {\n" +
                "  font-size: 56px !important;\n" +
                "  line-height: 60px !important;\n" +
                "}\n" +
                ".mso .size-64,\n" +
                ".ie .size-64 {\n" +
                "  font-size: 64px !important;\n" +
                "  line-height: 63px !important;\n" +
                "}\n" +
                "</style>\n" +
                "    \n" +
                "  <style type=\"text/css\">\n" +
                "body{background-color:#fff}.logo a:hover,.logo a:focus{color:#859bb1 !important}.mso .layout-has-border{border-top:1px solid #ccc;border-bottom:1px solid #ccc}.mso .layout-has-bottom-border{border-bottom:1px solid #ccc}.mso .border,.ie .border{background-color:#ccc}.mso h1,.ie h1{}.mso h1,.ie h1{font-size:64px !important;line-height:63px !important}.mso h2,.ie h2{}.mso h2,.ie h2{font-size:30px !important;line-height:38px !important}.mso h3,.ie h3{}.mso h3,.ie h3{font-size:22px !important;line-height:31px !important}.mso .layout__inner,.ie .layout__inner{}.mso .footer__share-button p{}.mso .footer__share-button p{font-family:sans-serif}\n" +
                "</style><meta name=\"robots\" content=\"noindex,nofollow\" />\n" +
                "<meta property=\"og:title\" content=\"My First Campaign\" />\n" +
                "</head>\n" +
                "<!--[if mso]>\n" +
                "  <body class=\"mso\">\n" +
                "<![endif]-->\n" +
                "<!--[if !mso]><!-->\n" +
                "  <body class=\"no-padding\" style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;\">\n" +
                "<!--<![endif]-->\n" +
                "    <table class=\"wrapper\" style=\"border-collapse: collapse;table-layout: fixed;min-width: 320px;width: 100%;background-color: #fff;\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tbody><tr><td>\n" +
                "      <div role=\"banner\">\n" +
                "        <div class=\"preheader\" style=\"Margin: 0 auto;max-width: 560px;min-width: 280px; width: 280px;width: calc(28000% - 167440px);\">\n" +
                "          <div style=\"border-collapse: collapse;display: table;width: 100%;\">\n" +
                "          <!--[if (mso)|(IE)]><table align=\"center\" class=\"preheader\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tr><td style=\"width: 280px\" valign=\"top\"><![endif]-->\n" +
                "            <div class=\"snippet\" style=\"display: table-cell;Float: left;font-size: 12px;line-height: 19px;max-width: 280px;min-width: 140px; width: 140px;width: calc(14000% - 78120px);padding: 10px 0 5px 0;color: #adb3b9;font-family: sans-serif;\">\n" +
                "              \n" +
                "            </div>\n" +
                "          <!--[if (mso)|(IE)]></td><td style=\"width: 280px\" valign=\"top\"><![endif]-->\n" +
                "            <div class=\"webversion\" style=\"display: table-cell;Float: left;font-size: 12px;line-height: 19px;max-width: 280px;min-width: 139px; width: 139px;width: calc(14100% - 78680px);padding: 10px 0 5px 0;text-align: right;color: #adb3b9;font-family: sans-serif;\">\n" +
                "              \n" +
                "            </div>\n" +
                "          <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "          </div>\n" +
                "        </div>\n" +
                "        <div class=\"header\" style=\"Margin: 0 auto;max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);\" id=\"emb-email-header-container\">\n" +
                "        <!--[if (mso)|(IE)]><table align=\"center\" class=\"header\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tr><td style=\"width: 600px\"><![endif]-->\n" +
                "          <div class=\"logo emb-logo-margin-box\" style=\"font-size: 26px;line-height: 32px;Margin-top: 6px;Margin-bottom: 20px;color: #c3ced9;font-family: Roboto,Tahoma,sans-serif;Margin-left: 20px;Margin-right: 20px;\" align=\"center\">\n" +
                "            <div class=\"logo-center\" align=\"center\" id=\"emb-email-header\"><img style=\"display: block;height: auto;width: 100%;border: 0;max-width: 191px;\" src=\"https://k61.kn3.net/D/5/0/E/6/8/433.png\" alt=\"\" width=\"191\" /></div>\n" +
                "          </div>\n" +
                "        <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "        </div>\n" +
                "      </div>\n" +
                "      <div role=\"section\">\n" +
                "      <div style=\"background-color: #e31212;\">\n" +
                "        <div class=\"layout three-col\" style=\"Margin: 0 auto;max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;\">\n" +
                "          <div class=\"layout__inner\" style=\"border-collapse: collapse;display: table;width: 100%;\">\n" +
                "          <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tr class=\"layout-full-width\" style=\"background-color: #e31212;\"><td class=\"layout__edges\">&nbsp;</td><td style=\"width: 200px\" valign=\"top\" class=\"w160\"><![endif]-->\n" +
                "            <div class=\"column\" style=\"Float: left;max-width: 320px;min-width: 200px; width: 320px;width: calc(72200px - 12000%);text-align: left;color: #8e959c;font-size: 14px;line-height: 21px;font-family: sans-serif;\">\n" +
                "            \n" +
                "              <div style=\"Margin-left: 20px;Margin-right: 20px;\">\n" +
                "      <div style=\"line-height:10px;font-size:1px\">&nbsp;</div>\n" +
                "    </div>\n" +
                "            \n" +
                "            </div>\n" +
                "          <!--[if (mso)|(IE)]></td><td style=\"width: 200px\" valign=\"top\" class=\"w160\"><![endif]-->\n" +
                "            <div class=\"column\" style=\"Float: left;max-width: 320px;min-width: 200px; width: 320px;width: calc(72200px - 12000%);text-align: left;color: #8e959c;font-size: 14px;line-height: 21px;font-family: sans-serif;\">\n" +
                "            \n" +
                "              <div style=\"Margin-left: 20px;Margin-right: 20px;\">\n" +
                "      <div style=\"line-height:10px;font-size:1px\">&nbsp;</div>\n" +
                "    </div>\n" +
                "            \n" +
                "            </div>\n" +
                "          <!--[if (mso)|(IE)]></td><td style=\"width: 200px\" valign=\"top\" class=\"w160\"><![endif]-->\n" +
                "            <div class=\"column\" style=\"Float: left;max-width: 320px;min-width: 200px; width: 320px;width: calc(72200px - 12000%);text-align: left;color: #8e959c;font-size: 14px;line-height: 21px;font-family: sans-serif;\">\n" +
                "            \n" +
                "              <div style=\"Margin-left: 20px;Margin-right: 20px;\">\n" +
                "      <div style=\"line-height:10px;font-size:1px\">&nbsp;</div>\n" +
                "    </div>\n" +
                "            \n" +
                "            </div>\n" +
                "          <!--[if (mso)|(IE)]></td><td class=\"layout__edges\">&nbsp;</td></tr></table><![endif]-->\n" +
                "          </div>\n" +
                "        </div>\n" +
                "      </div>\n" +
                "  \n" +
                "      <div style=\"background-color: #e31212;\">\n" +
                "        <div class=\"layout one-col\" style=\"Margin: 0 auto;max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;\">\n" +
                "          <div class=\"layout__inner\" style=\"border-collapse: collapse;display: table;width: 100%;\">\n" +
                "          <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tr class=\"layout-full-width\" style=\"background-color: #e31212;\"><td class=\"layout__edges\">&nbsp;</td><td style=\"width: 600px\" class=\"w560\"><![endif]-->\n" +
                "            <div class=\"column\" style=\"max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);text-align: left;color: #8e959c;font-size: 14px;line-height: 21px;font-family: sans-serif;\">\n" +
                "            \n" +
                "              <div style=\"Margin-left: 20px;Margin-right: 20px;\">\n" +
                "      <div style=\"line-height:10px;font-size:1px\">&nbsp;</div>\n" +
                "    </div>\n" +
                "            \n" +
                "            </div>\n" +
                "          <!--[if (mso)|(IE)]></td><td class=\"layout__edges\">&nbsp;</td></tr></table><![endif]-->\n" +
                "          </div>\n" +
                "        </div>\n" +
                "      </div>\n" +
                "  \n" +
                "      <div style=\"background-color: #4b5462;background-position: 0px 0px;background-image: url(https://i1.createsend1.com/ei/j/FA/EA6/A1A/133854/csfinal/dog_heart.jpg);background-repeat: repeat;\">\n" +
                "        <div class=\"layout one-col\" style=\"Margin: 0 auto;max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;\">\n" +
                "          <div class=\"layout__inner\" style=\"border-collapse: collapse;display: table;width: 100%;\">\n" +
                "          <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tr class=\"layout-full-width\" style=\"background-color: #4b5462;background-position: 0px 0px;background-image: url(https://i1.createsend1.com/ei/j/FA/EA6/A1A/133854/csfinal/dog_heart.jpg);background-repeat: repeat;\"><td class=\"layout__edges\">&nbsp;</td><td style=\"width: 600px\" class=\"w560\"><![endif]-->\n" +
                "            <div class=\"column\" style=\"max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);text-align: left;color: #8e959c;font-size: 14px;line-height: 21px;font-family: sans-serif;\">\n" +
                "            \n" +
                "              <div style=\"Margin-left: 20px;Margin-right: 20px;\">\n" +
                "      <div style=\"line-height:110px;font-size:1px\">&nbsp;</div>\n" +
                "    </div>\n" +
                "            \n" +
                "              <div style=\"Margin-left: 20px;Margin-right: 20px;\">\n" +
                "      <h1 class=\"size-64\" style=\"Margin-top: 0;Margin-bottom: 20px;font-style: normal;font-weight: normal;color: #000;font-size: 44px;line-height: 50px;font-family: avenir,sans-serif;text-align: center;\" lang=\"x-size-64\"><span class=\"font-avenir\"><strong><span style=\"color:#ffffff\">Recibiste una Solicitud de Reserva!</span></strong></span></h1>\n" +
                "    </div>\n" +
                "            \n" +
                "              <div style=\"Margin-left: 20px;Margin-right: 20px;\">\n" +
                "      <div style=\"line-height:5px;font-size:1px\">&nbsp;</div>\n" +
                "    </div>\n" +
                "            \n" +
                "              <div style=\"Margin-left: 20px;Margin-right: 20px;\">\n" +
                "      <div class=\"btn btn--flat btn--medium\" style=\"Margin-bottom: 20px;text-align: center;\">\n" +
                "        <![if !mso]><a style=\"border-radius: 4px;display: inline-block;font-size: 12px;font-weight: bold;line-height: 22px;padding: 10px 20px;text-align: center;text-decoration: none !important;transition: opacity 0.1s ease-in;color: #ffffff !important;background-color: #e31212;font-family: Avenir, sans-serif;\" href=\"http://localhost:5000/views/reserva/mis-reservas-user.html?status=CONFIRMATION_PENDING\">Ver Detalles</a><![endif]>\n" +
                "      <!--[if mso]><p style=\"line-height:0;margin:0;\">&nbsp;</p><v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" href=\"http://test.com\" style=\"width:108px\" arcsize=\"10%\" fillcolor=\"#E31212\" stroke=\"f\"><v:textbox style=\"mso-fit-shape-to-text:t\" inset=\"0px,9px,0px,9px\"><center style=\"font-size:12px;line-height:22px;color:#FFFFFF;font-family:Avenir,sans-serif;font-weight:bold;mso-line-height-rule:exactly;mso-text-raise:4px\">Ver Detalles</center></v:textbox></v:roundrect><![endif]--></div>\n" +
                "    </div>\n" +
                "            \n" +
                "              <div style=\"Margin-left: 20px;Margin-right: 20px;\">\n" +
                "      <div style=\"line-height:85px;font-size:1px\">&nbsp;</div>\n" +
                "    </div>\n" +
                "            \n" +
                "            </div>\n" +
                "          <!--[if (mso)|(IE)]></td><td class=\"layout__edges\">&nbsp;</td></tr></table><![endif]-->\n" +
                "          </div>\n" +
                "        </div>\n" +
                "      </div>\n" +
                "  \n" +
                "      <div style=\"line-height:15px;font-size:15px;\">&nbsp;</div>\n" +
                "  \n" +
                "      \n" +
                "      <div role=\"contentinfo\">\n" +
                "        <div class=\"layout email-footer\" style=\"Margin: 0 auto;max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;\">\n" +
                "          <div class=\"layout__inner\" style=\"border-collapse: collapse;display: table;width: 100%;\">\n" +
                "          <!--[if (mso)|(IE)]><table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tr class=\"layout-email-footer\"><td style=\"width: 400px;\" valign=\"top\" class=\"w360\"><![endif]-->\n" +
                "            <div class=\"column wide\" style=\"text-align: left;font-size: 12px;line-height: 19px;color: #adb3b9;font-family: sans-serif;Float: left;max-width: 400px;min-width: 320px; width: 320px;width: calc(8000% - 47600px);\">\n" +
                "              <div style=\"Margin-left: 20px;Margin-right: 20px;Margin-top: 10px;Margin-bottom: 10px;\">\n" +
                "                \n" +
                "                <div style=\"font-size: 12px;line-height: 19px;\">\n" +
                "                  <div>&#169; 2017 Pupi - Todos los derechos reservados.</div>\n" +
                "                </div>\n" +
                "                <div style=\"font-size: 12px;line-height: 19px;Margin-top: 18px;\">\n" +
                "                  \n" +
                "                </div>\n" +
                "                <!--[if mso]>&nbsp;<![endif]-->\n" +
                "              </div>\n" +
                "            </div>\n" +
                "          <!--[if (mso)|(IE)]></td><td style=\"width: 200px;\" valign=\"top\" class=\"w160\"><![endif]-->\n" +
                "            <div class=\"column narrow\" style=\"text-align: left;font-size: 12px;line-height: 19px;color: #adb3b9;font-family: sans-serif;Float: left;max-width: 320px;min-width: 200px; width: 320px;width: calc(72200px - 12000%);\">\n" +
                "              <div style=\"Margin-left: 20px;Margin-right: 20px;Margin-top: 10px;Margin-bottom: 10px;\">\n" +
                "                \n" +
                "              </div>\n" +
                "            </div>\n" +
                "          <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "          </div>\n" +
                "        </div>\n" +
                "        <div class=\"layout one-col email-footer\" style=\"Margin: 0 auto;max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;\">\n" +
                "          <div class=\"layout__inner\" style=\"border-collapse: collapse;display: table;width: 100%;\">\n" +
                "          <!--[if (mso)|(IE)]><table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tr class=\"layout-email-footer\"><td style=\"width: 600px;\" class=\"w560\"><![endif]-->\n" +
                "            <div class=\"column\" style=\"text-align: left;font-size: 12px;line-height: 19px;color: #adb3b9;font-family: sans-serif;max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);\">\n" +
                "              <div style=\"Margin-left: 20px;Margin-right: 20px;Margin-top: 10px;Margin-bottom: 10px;\">\n" +
                "                <div style=\"font-size: 12px;line-height: 19px;\">\n" +
                "                  <span><preferences style=\"text-decoration: underline;\" lang=\"en\">Preferences</preferences>&nbsp;&nbsp;|&nbsp;&nbsp;</span><unsubscribe style=\"text-decoration: underline;\">Unsubscribe</unsubscribe>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "          <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "          </div>\n" +
                "        </div>\n" +
                "      </div>\n" +
                "      <div style=\"line-height:40px;font-size:40px;\">&nbsp;</div>\n" +
                "    </div></td></tr></tbody></table>\n" +
                "  \n" +
                "</body></html>\n";

        Destination destination = new Destination().withToAddresses(to);
        Content subjectContent = new Content().withData(subject);
        Content textContent = new Content().withData(emailTemplate);
        Body body = new Body().withHtml(textContent);
        Message message = new Message().withSubject(subjectContent).withBody(body);

        return new SendEmailRequest().withSource(from).withDestination(destination).withMessage(message);

    }
}
