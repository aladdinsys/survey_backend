package aladdinsys.aladdin_survey.global.constant;

import lombok.Getter;

@Getter
public enum ApiKeyPath {
	OPENAPI("open-api"),
	;

	private final String path;
	ApiKeyPath(String path) {
		this.path = path;
	}
}
