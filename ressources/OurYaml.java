import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;
/**
* @author Nina Trilck
*/

public class OurYaml {

	 OurYaml(final String yaml_input) throws MalformedURLException, IOException, URISyntaxException
	 {
	        // get all data from the YAML file
	        final Map<String, Object> l_data = (Map<String, Object>) new Yaml().load( OurYaml.getResourceURL( new File( yaml_input ) ).openStream() );

	  }
	 
	    private static URL getResourceURL(final File p_file ) throws MalformedURLException, URISyntaxException
	    {
	        try
	        {
	            if ( p_file.exists() )
	                return p_file.toURI().normalize().toURL();
	            return OurYaml.class.getClassLoader().getResource( p_file.toString().replace( File.separator, "/" ) ).toURI().normalize().toURL();
	        }
	        catch ( final Exception l_exception )
	        {
	            System.err.println( MessageFormat.format( "source [{0}] not found", p_file ) );
	            throw l_exception;
	        }
	    }
}
