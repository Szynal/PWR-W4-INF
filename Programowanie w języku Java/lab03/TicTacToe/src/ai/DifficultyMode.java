package ai;

import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PACKAGE;

import java.lang.annotation.Target;

import app.TicTacToeApp.Difficulty;

@Target({ METHOD, LOCAL_VARIABLE, PACKAGE })
public @interface DifficultyMode {

	Difficulty value();

	int[] easyMode();
}
