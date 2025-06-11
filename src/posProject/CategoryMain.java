package posProject;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CategoryMain {

	//public static void main(String[] args) {
	public static void run(Scanner sc) throws IOException {
		//Scanner sc = new Scanner(System.in);
		CategoryDAO categoryDAO = new CategoryDAO();
		

		String format = "|%8s|%18s|%7s|%27s|\n";
		String line = "=".repeat(82);
		
		while (true) {
			List<CategoryVO> categoryList = categoryDAO.categorySelectAll();
			System.out.printf(format, center("카테고리 번호", 8), center("카테고리명", 18), center("이모티콘", 7), center("설명", 27));
			System.out.println(line);
			for (CategoryVO vo : categoryList) {
				System.out.printf(format, center(String.valueOf(vo.getCategoryId()), 14),
						center(vo.getCategoryName(), 20), center(vo.getCategoryEmoji(), 10),
						center(vo.getCategoryDesc(), 30));
			}
			System.out.println(line);
			System.out.println("1 .카테고리 추가하기 | 2. 카테고리 삭제하기 | 3. 카테고리 수정하기 | 0. 되돌아가기");
			System.out.println(line);

			System.out.print("\t\t 입력 > ");
			int num = sc.nextInt();
			sc.nextLine();
			switch (num) {
			case 1: // 카테고리 추가하기
				System.out.println("(아무것도 입력하지 않으면 추가가 취소됩니다)");
				System.out.print("카테고리 이름> ");
				String name = sc.nextLine();
				System.out.print("이모티콘> ");
				String emoji = sc.nextLine();
				System.out.print("설명(200자 이하)> ");
				String desc = sc.nextLine();

				if (name.equals("") && emoji.equals("") && desc.equals("")) {
					System.out.println("추가하지 않았습니다\n");

					break;
				} else {
					if (name.equals("")) {
						System.out.println("카테고리명은 공백이 될 수 없습니다\n");

						break;
					} else {
						int co1 = categoryDAO.categoryInsert(name, emoji, desc);

						break;
					}
				}
			case 2: // 카테고리 삭제하기
				System.out.print("카테고리 번호> ");
				num = sc.nextInt();
				sc.nextLine();
				if (num == 0) {
					System.out.println("이전화면으로 되돌아갑니다");
					break;
				} else {
					System.out.println("정말로 삭제하시겠습니까?\n y.예\t n.아니오");
					String yn = sc.nextLine();
					if (yn.equals("y")) {
						int co2 = categoryDAO.categoryDicoyDelete(num);

						break;
					} else {
						if (yn.equals("n")) {
							System.out.println("취소되었습니다.\n 초기화면으로 돌아갑니다.");

							break;
						}
					}
				}
				break;

			case 3: // 카테고리 수정하기
				System.out.println("변경하고 싶은 카테고리의 번호를 입력해주세요");
				System.out.print("입력> ");
				num = sc.nextInt();
				sc.nextLine();
				System.out.println("선텍된 번호: " + num);
				System.out.println(line);
				System.out.print("변경할 카테고리명: ");
				name = sc.nextLine();
				System.out.print("변경할 이모티콘: ");
				emoji = sc.nextLine();
				System.out.print("변경할 카테고리 설명(200자 이내): ");
				desc = sc.nextLine();
				int co3 = categoryDAO.categoryUpdate(name, emoji, desc, num);

				break;
			case 0: // 되돌아가기

				return;
			}

		}

	}

	public static String center(String text, int width) {
		if (text == null)
			text = "";
		int padSize = width - text.length();
		int padStart = padSize / 2;
		int padEnd = padSize - padStart;
		return " ".repeat(Math.max(padStart, 0)) + text + " ".repeat(Math.max(padEnd, 0));
	}

}
