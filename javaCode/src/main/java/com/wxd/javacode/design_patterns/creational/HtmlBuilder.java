package com.wxd.javacode.design_patterns.creational;

public class HtmlBuilder {

    private HeadingBuilder headingBuilder = new HeadingBuilder();
    private HrBuilder hrBuilder = new HrBuilder();
    private ParagraphBuilder paragraphBuilder = new ParagraphBuilder();
    private QuoteBuilder quoteBuilder = new QuoteBuilder();

    public String toHtml(String markDown){
        StringBuilder buffer = new StringBuilder();
        markDown.lines().forEach(line -> {
            if (line.startsWith("#")) {
                buffer.append(headingBuilder.buildHeading(line)).append('\n');
            } else if (line.startsWith(">")) {
                buffer.append(quoteBuilder.buildQuote(line)).append('\n');
            } else if (line.startsWith("---")) {
                buffer.append(hrBuilder.buildHr(line)).append('\n');
            } else {
                buffer.append(paragraphBuilder.buildParagraph(line)).append('\n');
            }
        });
        return buffer.toString();
    }

    static class HeadingBuilder {
        public String buildHeading(String line) {
            int n = 0;
            while (line.charAt(0) == '#') {
                n++;
                line = line.substring(1);
            }
            return String.format("<h%d>%s</h%d>", n, line.strip(), n);
        }
    }

    static class HrBuilder {
        public String buildHr(String line) {
            return "<hr/>";
        }
    }

    static class ParagraphBuilder {
        public String buildParagraph(String line) {
            return "<p>" + line + "</p>";
        }
    }

    static class QuoteBuilder {
        public String buildQuote(String line) {
            return "<blockquote>" + line.substring(1).strip() + "</blockquote>";
        }
    }
}
