package programe;

import java.io.*;
import java.util.Scanner;
public class NotePad {
        private static final Scanner scanner = new Scanner(System.in);
        // 笔记保存到项目根目录的 notes 文件夹（IDEA 会自动创建）
        private static final String NOTE_DIR = System.getProperty("user.dir") + "/notes/";

        public static void main(String[] args) {
            // 初始化笔记目录（不存在则自动创建）
            File dir = new File(NOTE_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 主菜单循环
            while (true) {
                System.out.println("\n===== Java 控制台记事本 =====");
                System.out.println("1. 新建笔记");
                System.out.println("2. 查看笔记");
                System.out.println("3. 编辑笔记");
                System.out.println("4. 列出所有笔记");
                System.out.println("5. 删除笔记");
                System.out.println("0. 退出");
                System.out.print("请选择操作：");
                int choice = scanner.nextInt();
                scanner.nextLine(); // 吸收换行符，避免输入异常

                switch (choice) {
                    case 1:
                        createNote();
                        break;
                    case 2:
                        viewNote();
                        break;
                    case 3:
                        editNote();
                        break;
                    case 4:
                        listNotes();
                        break;
                    case 5:
                        deleteNote();
                        break;
                    case 0:
                        System.out.println("👋 退出记事本～");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("❌ 输入错误，请选择 0-5 的数字！");
                }
            }
        }

        // 1. 新建笔记
        private static void createNote() {
            System.out.print("📝 请输入笔记名称（无需.txt）：");
            String name = scanner.nextLine();
            String path = NOTE_DIR + name + ".txt";

            System.out.println("✏️ 请输入笔记内容（输入『结束』退出编辑）：");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
                String content;
                while (true) {
                    content = scanner.nextLine();
                    if ("结束".equals(content)) {
                        break;
                    }
                    writer.write(content);
                    writer.newLine(); // 换行
                }
                System.out.println("✅ 笔记保存成功！路径：" + path);
            } catch (IOException e) {
                System.out.println("❌ 保存失败：" + e.getMessage());
            }
        }

        // 2. 查看笔记
        private static void viewNote() {
            System.out.print("🔍 请输入要查看的笔记名称（无需.txt）：");
            String name = scanner.nextLine();
            String path = NOTE_DIR + name + ".txt";
            File file = new File(path);

            if (!file.exists()) {
                System.out.println("❌ 笔记不存在！");
                return;
            }

            System.out.println("\n===== 笔记内容 =====");
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println("❌ 读取失败：" + e.getMessage());
            }
        }

        // 3. 编辑笔记（追加内容）
        private static void editNote() {
            System.out.print("✏️ 请输入要编辑的笔记名称（无需.txt）：");
            String name = scanner.nextLine();
            String path = NOTE_DIR + name + ".txt";
            File file = new File(path);

            if (!file.exists()) {
                System.out.println("❌ 笔记不存在！");
                return;
            }

            System.out.println("✏️ 请输入要追加的内容（输入『结束』退出编辑）：");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) { // true = 追加模式
                String content;
                while (true) {
                    content = scanner.nextLine();
                    if ("结束".equals(content)) {
                        break;
                    }
                    writer.write(content);
                    writer.newLine();
                }
                System.out.println("✅ 笔记编辑成功！");
            } catch (IOException e) {
                System.out.println("❌ 编辑失败：" + e.getMessage());
            }
        }

        // 4. 列出所有笔记
        private static void listNotes() {
            File dir = new File(NOTE_DIR);
            File[] files = dir.listFiles((d, name) -> name.endsWith(".txt")); // 只显示txt文件

            if (files == null || files.length == 0) {
                System.out.println("📂 暂无笔记！");
                return;
            }

            System.out.println("\n===== 所有笔记 =====");
            for (int i = 0; i < files.length; i++) {
                String name = files[i].getName().replace(".txt", "");
                System.out.println((i + 1) + ". " + name);
            }
        }

        // 5. 删除笔记
        private static void deleteNote() {
            System.out.print("🗑️ 请输入要删除的笔记名称（无需.txt）：");
            String name = scanner.nextLine();
            String path = NOTE_DIR + name + ".txt";
            File file = new File(path);

            if (!file.exists()) {
                System.out.println("❌ 笔记不存在！");
                return;
            }

            if (file.delete()) {
                System.out.println("✅ 笔记删除成功！");
            } else {
                System.out.println("❌ 删除失败！");
            }
        }
    }
