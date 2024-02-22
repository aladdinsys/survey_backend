package aladdinsys.aladdin_survey.global.constant;

import lombok.Getter;

@Getter
public enum ExtractPath {

	OPEN_API("open-api"),
	AUTHENTICATION("auth"),
	ERROR("error"),

	;

	private final String path;

	ExtractPath(String path) {
		this.path = path;
	}

}
