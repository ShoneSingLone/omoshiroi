package shone.sing.lone.swing.table.JavaConsoleOutput;

PipedInputStream pipedIS = new PipedInputStream();
PipedOutputStream pipedOS = new PipedOutputStream();

try {
   pipedOS.connect(pipedIS);
}
catch(IOException e) {
   System.err.println("连接失败");
   System.exit(1);
}

PrintStream ps = new PrintStream(pipedOS);
System.setOut(ps);
System.setErr(ps);
