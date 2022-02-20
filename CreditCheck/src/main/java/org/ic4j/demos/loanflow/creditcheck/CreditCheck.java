package org.ic4j.demos.loanflow.creditcheck;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import org.ic4j.agent.Agent;
import org.ic4j.agent.AgentBuilder;
import org.ic4j.agent.ProxyBuilder;
import org.ic4j.agent.ReplicaTransport;
import org.ic4j.agent.http.ReplicaApacheHttpTransport;
import org.ic4j.types.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreditCheck {
	static String PROPERTIES_FILE_NAME = "application.properties";
	static Short DEFAULT_RATING = 500;

    static Logger LOG = LoggerFactory.getLogger(CreditCheck .class);
	static String urlConnection = "jdbc:derby:dfinity;create=true";
	static Connection connection;

    static void setup() {
		try {
			connection = DriverManager.getConnection(urlConnection);
		} catch (Exception e) {
			LOG.error(e.getLocalizedMessage(), e);
		}
		try {
			Statement statement = connection.createStatement();
			String sql = "DROP TABLE data";
			statement.execute(sql);
		} catch (Exception e) {
			LOG.error(e.getLocalizedMessage(), e);
		}

		try {
			Statement statement = connection.createStatement();
			String sql = "CREATE TABLE data (ssn VARCHAR(11) PRIMARY KEY,rating INT)";
			statement.execute(sql);
			sql = "INSERT INTO data VALUES ('111-11-1111',550)";
			statement.execute(sql);
			sql = "INSERT INTO data VALUES ('222-22-2222',650)";
			statement.execute(sql);
			sql = "INSERT INTO data VALUES ('333-33-3333',750)";
			statement.execute(sql);			
		} catch (Exception e) {
			LOG.error(e.getLocalizedMessage(), e);
		}
	}
    public static void main(String[] args) {	
		setup();

		try {
			InputStream propInputStream = CreditCheck.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME);

			Properties env = new Properties();
			env.load(propInputStream);

			String icLocation = env.getProperty("ic.location");
			String icCanister = env.getProperty("ic.canister");
	
			ReplicaTransport transport = ReplicaApacheHttpTransport.create(icLocation);
			Agent agent = new AgentBuilder().transport(transport).build();

			CreditService creditService =  ProxyBuilder.create(agent,Principal.fromString(icCanister)).getProxy(CreditService.class);	
			
			CreditRequest[] requests = creditService.getRequests();

			PreparedStatement statement = connection.prepareStatement("SELECT ssn, rating FROM data WHERE ssn = ?" );
			
			for(CreditRequest request : requests)
			{
				LOG.info("Processing credit request for user " + request.userId +  " with SSN " + request.ssn);
				statement.setString(1, request.ssn);
				ResultSet result = statement.executeQuery();

				boolean found = false;
				while (result.next()) {
					Short rating = result.getShort("rating"); 

					creditService.setCredit(request.userId, rating);
					found = true;

					LOG.info("Rating is set to " + rating);
				}

				// set default rating if not found
				if(!found)
				{
					creditService.setCredit(request.userId, DEFAULT_RATING);
					LOG.info("Rating is set to " + DEFAULT_RATING);
				}
			}
			statement.closeOnCompletion();
		} catch (Exception e) {
			LOG.error(e.getLocalizedMessage(), e);
		}
	}
}
