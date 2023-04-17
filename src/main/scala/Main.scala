import com.kyledinh.sudoku.*

@main def hello: Unit =
  println("Solving a Sudoku problem...")

val s1 = Sudoku.loadFromResource("puzzles/sudoku_5.txt")
val answer = Sudoku.simulate(s1)
