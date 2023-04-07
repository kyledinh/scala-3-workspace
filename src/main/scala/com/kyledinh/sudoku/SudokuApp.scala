package com.kyledinh.sudoku

object SudokuApp extends App {

  val grid = Sudoku.loadFromResource("puzzles/sudoku_5.txt")
  Sudoku.simulate(grid)

}
