package shone.sing.lone.swing.table.JavaConsoleOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.Document;

public class ConsoleTextArea extends JTextArea {
	
	public void info(Object smt) {
		System.out.println(smt);
	}
	public ConsoleTextArea(InputStream[] inStreams) {
		for (int i = 0; i < inStreams.length; ++i)
			startConsoleReaderThread(inStreams[i]);
	} // ConsoleTextArea()

	public ConsoleTextArea() throws IOException {
		final LoopedStreams ls = new LoopedStreams();

		// 重定向System.out和System.err
		PrintStream ps = new PrintStream(ls.getOutputStream());
		System.setOut(ps);
		System.setErr(ps);

		startConsoleReaderThread(ls.getInputStream());
	} // ConsoleTextArea()

	public void getFrame() {
		JFrame f = new JFrame("ConsoleTextArea测试");
		ConsoleTextArea consoleTextArea = null;

		try {
			consoleTextArea = new ConsoleTextArea();
		} catch (IOException e) {
			System.err.println("不能创建LoopedStreams：" + e);
			System.exit(1);
		}

		consoleTextArea.setFont(java.awt.Font.decode("monospaced"));
		f.getContentPane().add(new JScrollPane(consoleTextArea), java.awt.BorderLayout.CENTER);
		f.setBounds(50, 50, 300, 300);
		f.setVisible(true);

		f.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				System.exit(0);
			}
		});
		
	}
	private void startConsoleReaderThread(InputStream inStream) {
		final BufferedReader br = new BufferedReader(new InputStreamReader(inStream));
		new Thread(new Runnable() {
			public void run() {
				StringBuffer sb = new StringBuffer();
				try {
					String s;
					Document doc = getDocument();
					while ((s = br.readLine()) != null) {
						boolean caretAtEnd = false;
						caretAtEnd = getCaretPosition() == doc.getLength() ? true : false;
						sb.setLength(0);
						append(sb.append(s).append('\n').toString());
						if (caretAtEnd)
							setCaretPosition(doc.getLength());
					}
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "从BufferedReader读取错误：" + e);
					System.exit(1);
				}
			}
		}).start();
	} // startConsoleReaderThread()
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConsoleTextArea console = null;
		try {
			 console =new ConsoleTextArea();
			 console.getFrame();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, String> map0 = new HashMap<String, String>();
		map0.put("name", "zhangsan");
		map0.put("***", "female");
		System.out.println(map0);
		System.out.println(map0.get("name"));
	}
} 
