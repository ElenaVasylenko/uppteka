package com.upp.apteka.config;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.upp.apteka.bo.Pharmacy;
import com.upp.apteka.service.PharmacyService;

@Configuration
@ComponentScan(basePackages = "com.upp.apteka")
@EnableAspectJAutoProxy
@PropertySource("classpath:jdbc.properties")
@PropertySource("classpath:pharmacy.properties")
@Import({ HibernateConfig.class })
public class AppConfig {

	@Autowired
	private Environment environment;

	@Autowired
	private Mapper mapper;

	@Autowired
	private PharmacyService pharmacyService;

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("jdbc.databaseurl"));
		dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));

		return dataSource;

	}

	@Bean
	public Pharmacy pharmacyId() {
		return pharmacyService.getPharmacy(Long.valueOf(environment.getRequiredProperty("pharmacy.id")));
	}

	@Bean
	public JFrame getDispatcherFrame() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {

		JFrame dispatcherFrame = new JFrame();

		dispatcherFrame.setTitle("Аптека");
		dispatcherFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dispatcherFrame.setMinimumSize(new Dimension(800, 700));
		// dispatcherFrame.setResizable(false);
		dispatcherFrame.setLocationRelativeTo(null);
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		// add main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		dispatcherFrame.setContentPane(mainPanel);
		dispatcherFrame.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

		// SHSHSHSSHSHSHSHSSHHSHSHSHS=======================================SHSHSHSSHSHSHSHSSHHSHSHSHS

		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new GridLayout());
		labelPanel.setBackground(new Color(255, 255, 255));

		JLabel menuLabel = new JLabel("Головне меню");
		menuLabel.setFont(new Font(menuLabel.getFont().getName(), Font.PLAIN, 18));
		menuLabel.setVerticalAlignment(JLabel.CENTER);
		menuLabel.setHorizontalAlignment(JLabel.LEFT);
		menuLabel.setBorder(new EmptyBorder(5, 10, 5, 10));

		labelPanel.add(menuLabel);
		labelPanel.setMaximumSize( new Dimension((int) labelPanel.getMaximumSize().getWidth(), (int) labelPanel.getPreferredSize().getHeight()) );
		mainPanel.add(labelPanel);

		JPanel menuPanel = new JPanel();

		JButton patientMenuButton = new JButton("Переглянути пацієнтів");
		patientMenuButton.addActionListener((ActionEvent arg0) -> {

				Map<String, Object> params = new HashMap<String, Object>();
				params.put("current", 1);

				mapper.changeActivity("allPatients", params);

			});

		JButton doctorMenuButton = new JButton("Переглянути лікарів");
		doctorMenuButton.addActionListener((ActionEvent arg0) -> {

				Map<String, Object> params = new HashMap<String, Object>();
				params.put("current", 1);

				mapper.changeActivity("allDoctors", params);

			});

		JButton purchaseMenuButton = new JButton("Переглянути покупки");
		purchaseMenuButton.addActionListener((ActionEvent arg0) -> {

				Map<String, Object> params = new HashMap<String, Object>();
				params.put("current", 1);

				mapper.changeActivity("allPurchases", params);

			});

		JButton prescriptionMenuButton = new JButton("Переглянути рецепти");
		prescriptionMenuButton.addActionListener((ActionEvent arg0) -> {

				Map<String, Object> params = new HashMap<String, Object>();
				params.put("current", 1);

				mapper.changeActivity("allPrescriptions", params);

			});

		JButton pharmacyMenuButton = new JButton("Переглянути аптеки");
		pharmacyMenuButton.addActionListener((ActionEvent e) -> {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("current", 1);

				mapper.changeActivity("allPharmacies", params);

			});

		JButton medicineMenuButton = new JButton("Переглянути ліки");
		medicineMenuButton.addActionListener((ActionEvent e) -> {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("current", 1);

				mapper.changeActivity("allMedicines", params);

			});

		JButton pharmacyMedicineMenuButton = new JButton("Переглянути ліки аптеки");
		pharmacyMedicineMenuButton.addActionListener((ActionEvent e) -> {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("current", 1);

				mapper.changeActivity("allPharmacyMedicines", params);

			});

		JButton deliveryMenuButton = new JButton("Переглянути поставки");
		deliveryMenuButton.addActionListener((ActionEvent e) -> {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("current", 1);

				mapper.changeActivity("allDeliveries", params);

			});

		menuPanel.add(patientMenuButton);
		menuPanel.add(doctorMenuButton);
		menuPanel.add(purchaseMenuButton);
		menuPanel.add(prescriptionMenuButton);
		menuPanel.add(pharmacyMenuButton);
		menuPanel.add(medicineMenuButton);
		menuPanel.add(pharmacyMedicineMenuButton);
		menuPanel.add(deliveryMenuButton);

		mainPanel.add(menuPanel);
		mainPanel.add(Box.createVerticalGlue()); // add filler

		// SHSHSHSSHSHSHSHSSHHSHSHSHS=======================================SHSHSHSSHSHSHSHSSHHSHSHSHS

		// add main manu bar
		JMenuBar menuBar = new JMenuBar();

		// JManu for addition

		JMenu addMenu = new JMenu("Додавання");

		JMenuItem addDoctor = new JMenuItem("Додати лікаря");
		addDoctor.addActionListener(new ActionListener() {

			// @Override
			public void actionPerformed(ActionEvent e) {
				mapper.changeActivity("addDoctor", new HashMap<String, Object>());

			}
		});

		JMenuItem addPatient = new JMenuItem("Додати пацієнта");
		addPatient.addActionListener(new ActionListener() {

			// @Override
			public void actionPerformed(ActionEvent e) {
				mapper.changeActivity("addPatient", new HashMap<String, Object>());

			}
		});

		JMenuItem addPrescription = new JMenuItem("Додати рецепт");
		addPrescription.addActionListener(new ActionListener() {

			// @Override
			public void actionPerformed(ActionEvent e) {
				mapper.changeActivity("addPrescription", new HashMap<String, Object>());

			}
		});

		JMenuItem addPurchase = new JMenuItem("Додати покупку");
		addPurchase.addActionListener(new ActionListener() {

			// @Override
			public void actionPerformed(ActionEvent e) {
				mapper.changeActivity("selectPrescription", new HashMap<String, Object>());

			}
		});

		JMenuItem addPharmacy = new JMenuItem("Додати аптеку");
		addPharmacy.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				mapper.changeActivity("addPharmacy", new HashMap<String, Object>());

			}
		});

		JMenuItem addMedicine = new JMenuItem("Додати ліки");
		addMedicine.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				mapper.changeActivity("addMedicine", new HashMap<String, Object>());

			}
		});

		JMenuItem addDelivery = new JMenuItem("Додати поставку");
		addDelivery.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				mapper.changeActivity("addDelivery", new HashMap<String, Object>());

			}
		});

		// JManu for viewing

		JMenu viewMenu = new JMenu("Перегляд");

		JMenuItem allPatients = new JMenuItem("Перегляд пацієнтів");
		allPatients.addActionListener(new ActionListener() {

			// @Override
			public void actionPerformed(ActionEvent arg0) {

				Map<String, Object> params = new HashMap<String, Object>();
				params.put("current", 1);

				mapper.changeActivity("allPatients", params);

			}
		});

		JMenuItem allDoctors = new JMenuItem("Перегляд лікарів");
		allDoctors.addActionListener(new ActionListener() {

			// @Override
			public void actionPerformed(ActionEvent arg0) {

				Map<String, Object> params = new HashMap<String, Object>();
				params.put("current", 1);

				mapper.changeActivity("allDoctors", params);

			}
		});

		JMenuItem allPurchases = new JMenuItem("Перегляд покупок");
		allPurchases.addActionListener(new ActionListener() {

			// @Override
			public void actionPerformed(ActionEvent arg0) {

				Map<String, Object> params = new HashMap<String, Object>();
				params.put("current", 1);

				mapper.changeActivity("allPurchases", params);

			}
		});

		JMenuItem allPrescriptions = new JMenuItem("Перегляд рецептів");
		allPrescriptions.addActionListener(new ActionListener() {

			// @Override
			public void actionPerformed(ActionEvent arg0) {

				Map<String, Object> params = new HashMap<String, Object>();
				params.put("current", 1);

				mapper.changeActivity("allPrescriptions", params);

			}
		});

		JMenuItem allPharmacies = new JMenuItem("Перегляд аптек");
		allPharmacies.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("current", 1);

				mapper.changeActivity("allPharmacies", params);

			}
		});

		JMenuItem allMedicines = new JMenuItem("Перегляд ліків");
		allMedicines.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("current", 1);

				mapper.changeActivity("allMedicines", params);

			}
		});

		JMenuItem allPharmacyMedicines = new JMenuItem("Перегляд ліків аптеки");
		allPharmacyMedicines.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("current", 1);

				mapper.changeActivity("allPharmacyMedicines", params);

			}
		});

		JMenuItem allDeliveries = new JMenuItem("Перегляд поставок");
		allDeliveries.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("current", 1);

				mapper.changeActivity("allDeliveries", params);

			}
		});

		/*
		 * Reports
		 */
		JMenu chartMenu = new JMenu("Звіти");

		JMenuItem lossChart = new JMenuItem("Графік витрат");
		lossChart.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				mapper.changeActivity("lossChart", null);

			}
		});

		JMenuItem pharmacyLossChart = new JMenuItem("Графік витрат кожної аптеки");
		pharmacyLossChart.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				mapper.changeActivity("pharmacyLossChart", null);

			}
		});

		JMenuItem incomeChart = new JMenuItem("Графік доходів");
		incomeChart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mapper.changeActivity("incomeChart", null);

			}
		});

		JMenuItem pharmacyIncomeChart = new JMenuItem("Графік доходів кожної аптеки");
		pharmacyIncomeChart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mapper.changeActivity("pharmacyIncomeChart", null);

			}
		});

		JMenuItem profitChart = new JMenuItem("Графік прибутків");
		profitChart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mapper.changeActivity("profitChart", null);

			}
		});

		JMenuItem pharmacyProfitChart = new JMenuItem("Графік прибутків кожної аптеки");
		pharmacyProfitChart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mapper.changeActivity("pharmacyProfitChart", null);

			}
		});
		
		JMenuItem soldMedicineNum = new JMenuItem("Графік к-сті проданих одиниць ліків");
		soldMedicineNum.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mapper.changeActivity("soldMedicineNumChart", null);
				
			}
		});
		
		JMenuItem pharmacySoldMedicineNum = new JMenuItem("Графік к-сті проданих одиниць ліків у кожній аптеці");
		pharmacySoldMedicineNum.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mapper.changeActivity("pharmacySoldMedicineNumChart", null);
				
			}
		});

//		// JMenu for reports
//		chartMenu.add(pharmacyLossChart);
//		chartMenu.add(lossChart);
//		chartMenu.add(incomeChart);
//		chartMenu.add(pharmacyIncomeChart);
//		chartMenu.add(profitChart);
//		chartMenu.add(pharmacyProfitChart);
//		chartMenu.add(soldMedicineNum);
//		chartMenu.add(pharmacySoldMedicineNum);
//
//		// JMenu for viewing
//		viewMenu.add(allPharmacies);
//		viewMenu.add(allMedicines);
//		viewMenu.add(allPharmacyMedicines);
//		viewMenu.add(allDeliveries);
//		viewMenu.add(allDoctors);
//		viewMenu.add(allPatients);
//		viewMenu.add(allPrescriptions);
//		viewMenu.add(allPurchases);
//
//		// JMenu for addition
//		addMenu.add(addPharmacy);
//		addMenu.add(addMedicine);
//		addMenu.add(addDelivery);
//		addMenu.add(addDoctor);
//		addMenu.add(addPatient);
//		addMenu.add(addPrescription);
//		addMenu.add(addPurchase);
//
//		menuBar.add(addMenu);
//		menuBar.add(viewMenu);
//		menuBar.add(chartMenu);
//
//		dispatcherFrame.setJMenuBar(menuBar);

		return dispatcherFrame;
	}
}
