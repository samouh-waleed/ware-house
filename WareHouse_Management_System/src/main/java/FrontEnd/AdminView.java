package FrontEnd;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import controller.NewOrderState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class AdminView extends JFrame {

	private DefaultCategoryDataset dataset;
	private JTextArea serverInfoTextArea;

	static final String DB_URL = "jdbc:sqlite:src/main/java/model/wareHouseDatabase.db";

	public AdminView() {
		setTitle("Warehouse Product Monitor System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);

		// Create a panel for the bar graph
		JPanel graphPanel = createGraphPanel();
		// Create a panel for the server information
		JPanel infoPanel = createInfoPanel();

		// Create a split pane to hold the graph and server info panels
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, graphPanel, infoPanel);
		splitPane.setDividerLocation(500); // Adjust the initial divider location for balance

		// Back Button
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onBackButtonPressed();
			}
		});

		// Setting the layout for the top panel
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(backButton, BorderLayout.WEST); // Adding the button to the top-left

		// Adding panels to the frame
		add(topPanel, BorderLayout.NORTH);
		add(splitPane, BorderLayout.CENTER);

		// Simulate receiving data from the server
		simulateServerData();

		setLocationRelativeTo(null);
		setVisible(true);
	}

	private JPanel createGraphPanel() {
		JPanel graphPanel = new JPanel(new BorderLayout());

		// Create a dataset for the bar graph
		dataset = createDataset();
		JFreeChart chart = ChartFactory.createBarChart("Warehouse Product Monitor System", // Title
				"Product", // Category axis label
				"Quantity", // Value axis label
				dataset, PlotOrientation.VERTICAL, // Orientation
				true, // Include legend
				true, false);

		// Get the plot object for customizations
		CategoryPlot plot = chart.getCategoryPlot();

		// Custom colors for the series
		BarRenderer renderer = (BarRenderer) plot.getRenderer();

		// Define the custom colors
		Color[] colors = new Color[] { new Color(252, 88, 88), new Color(90, 124, 255), new Color(96, 255, 96),
				new Color(255, 255, 153), new Color(255, 160, 255) };

		for (int i = 0; i < dataset.getRowCount(); i++) {
			renderer.setSeriesPaint(i, colors[i % colors.length]);
		}

		renderer.setItemMargin(0.02);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(500, 300));

		graphPanel.add(chartPanel, BorderLayout.CENTER);

		return graphPanel;
	}

	private DefaultCategoryDataset createDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		fetchAndPopulateDataFromDatabase(dataset);

		return dataset;
	}

	private void fetchAndPopulateDataFromDatabase(DefaultCategoryDataset dataset) {
		try (Connection conn = DriverManager.getConnection(DB_URL)) {
			String query = "SELECT pName, quantity FROM product";
			try (PreparedStatement pstmt = conn.prepareStatement(query)) {
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					String productName = rs.getString("pName");
					int quantity = rs.getInt("quantity");
					dataset.addValue(quantity, productName, productName);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private JPanel createInfoPanel() {
		JPanel infoPanel = new JPanel(new BorderLayout());

		serverInfoTextArea = new JTextArea();
		serverInfoTextArea.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(serverInfoTextArea);
		scrollPane.setPreferredSize(new Dimension(300, 300)); 

		infoPanel.add(scrollPane, BorderLayout.CENTER);

		return infoPanel;
	}

	private void simulateServerData() {
		Timer timer = new Timer(2000, e -> {
			String orderInfo = "";
			if (!NewOrderState.orderQ.isEmpty()) {
				orderInfo = "Last Order\n======================\n" + "Product: "
						+ NewOrderState.orderQ.element().getProductID() + "\n" + "Quantity: "
						+ NewOrderState.orderQ.element().getQuantity() + "\n" + "TimeStamp:"
						+ NewOrderState.orderQ.element().getTimeStamp() + "\n";
			} else {
				orderInfo = "No Previous Orders\n";
			}

			fetchAndPopulateDataFromDatabase(dataset);

			updateServerInfo(orderInfo + "\nCurrent Product Quantity in Warehouse\n" + "======================\n"
					+ "Product 1 ===> " + dataset.getValue("Product 1", "Product 1") + "\n" + "Product 2 ===> "
					+ dataset.getValue("Product 2", "Product 2") + "\n" + "Product 3 ===> "
					+ dataset.getValue("Product 3", "Product 3") + "\n" + "Product 4 ===> "
					+ dataset.getValue("Product 4", "Product 4") + "\n" + "Product 5 ===> "
					+ dataset.getValue("Product 5", "Product 5") + "\n");
		});
		timer.start();
	}

	private void updateServerInfo(String info) {
		serverInfoTextArea.setText(info);
	}

	private void onBackButtonPressed() {
		HomePage homePage = new HomePage();
		this.setVisible(false);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new AdminView());
	}
}
