package javaFX2chartScatter;

import java.net.URL;
import java.util.Iterator;
import java.util.TreeMap;

import javafx.fxml.FXML;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

public class ChartScatterController {

	@FXML
	private VBox vbx;

	@FXML
	void initialize() {
		assert vbx != null : "fx:id=\"vbx\" was not injected: check your FXML file 'ChartScatter.fxml'.";

		XYChart.Series<Number, Number> series01 = new XYChart.Series<>();
		XYChart.Series<Number, Number> series02 = new XYChart.Series<>();
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		ScatterChart<Number, Number> scatterc = new ScatterChart<>(xAxis, yAxis);

		scatterc.prefWidthProperty().bind(vbx.widthProperty());
		scatterc.prefHeightProperty().bind(vbx.heightProperty());

		scatterc.setTitle("Scatter Chart Sample");
		xAxis.setLabel("X-Axis label");
		yAxis.setLabel("Y-Axis label");

		//////////////////////////////
		// set Data
		series01.setName("data01");
		{
			URL url = this.getClass().getResource("res/data01forScatter.csv");
			OpCsv csv = new OpCsv(url);

			TreeMap<Integer, String[]> map = csv.getNumberSortedCsv(0);
			Iterator<Integer> it = map.keySet().iterator();
			while (it.hasNext()) {
				int no = it.next();
				String[] words = map.get(no);
				Double d01 = Double.parseDouble(words[0]);
				Double d02 = Double.parseDouble(words[1]);

				series01.getData().add(new XYChart.Data<Number, Number>(d01, d02));
			}
		}
		scatterc.getData().add(series01);

		series02.setName("data02");
		{
			URL url = this.getClass().getResource("res/data02forScatter.csv");
			OpCsv csv = new OpCsv(url);

			TreeMap<Integer, String[]> map = csv.getNumberSortedCsv(0);
			Iterator<Integer> it = map.keySet().iterator();
			while (it.hasNext()) {
				int no = it.next();
				String[] words = map.get(no);
				Double d01 = Double.parseDouble(words[0]);
				Double d02 = Double.parseDouble(words[1]);

				series02.getData().add(new XYChart.Data<Number, Number>(d01, d02));
			}
		}
		scatterc.getData().add(series02);

		vbx.getChildren().clear();
		vbx.getChildren().add(scatterc);
	}
}
