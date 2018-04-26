package templater;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

//import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class PageGenerator {
//    private static final String HTML_DIR = "templates";
    private static PageGenerator pageGenerator;
    private final Configuration confg;

    private PageGenerator() {
        confg = new Configuration(Configuration.VERSION_2_3_0 );
    }

    public static PageGenerator instance() {
        if(pageGenerator == null)
            pageGenerator = new PageGenerator();
        return pageGenerator;
    }

    public String getPage(String fileName, Map<String, Object> data) {
        Writer stream = new StringWriter();
        try{
//           исключаем из параметров константу и separator
            Template template = confg.getTemplate(/*HTML_DIR + File.separator +*/ fileName);
            template.process(data, stream);
        }  catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return stream.toString();
    }


}
