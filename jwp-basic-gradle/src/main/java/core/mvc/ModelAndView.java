package core.mvc;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;


@Getter
public class ModelAndView {
    private final View view;
    private final Map<String, Object> model = new HashMap<>();

    public ModelAndView(View view) {
        this.view = view;
    }

    public ModelAndView addObject(String attributeName, Object attributeValue) {
        model.put(attributeName, attributeValue);
        return this;
    }
}