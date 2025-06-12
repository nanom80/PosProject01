package posproject.sub;

public class TextUtil {
	// 한글, 전각 문자 등 실제 출력 너비가 2칸인 문자들 처리
	public static int getDisplayWidth(String text) {
		int width = 0;
		for (char ch : text.toCharArray()) {
			if (isWideChar(ch))
				width += 2;
			else
				width += 1;
		}
		return width;
	}

	public static boolean isWideChar(char ch) {
		return (ch >= 0xAC00 && ch <= 0xD7A3) || // 한글
				(ch >= 0x1100 && ch <= 0x11FF) || // 한글 자모
				(ch >= 0x2E80 && ch <= 0x9FFF) || // CJK
				(ch >= 0xFF01 && ch <= 0xFF60) || // 전각 라틴 문자
				(ch >= 0xFFE0 && ch <= 0xFFE6); // 전각 기호
	}

	public static String padRightDisplay(String text, int totalDisplayWidth) {
		int actualWidth = getDisplayWidth(text);
		int pad = totalDisplayWidth - actualWidth;
		return text + " ".repeat(Math.max(pad, 0));
	}
}
