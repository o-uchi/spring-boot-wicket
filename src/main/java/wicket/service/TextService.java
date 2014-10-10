package wicket.service;

import org.springframework.stereotype.Service;

@Service
public class TextService {

	public String getText() {
		return "delivered by spring service";
	}
}
