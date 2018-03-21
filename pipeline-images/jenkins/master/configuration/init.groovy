import java.util.logging.Logger
import jenkins.security.s2m.*
import jenkins.model.*;
import com.redhat.jenkins.plugins.ci.*
import com.redhat.jenkins.plugins.ci.messaging.*

def logger = Logger.getLogger("")

logger.info("Disabling CLI over remoting")
jenkins.CLI.get().setEnabled(false);

logger.info("Enable Slave -> Master Access Control")
Jenkins.instance.injector.getInstance(AdminWhitelistRule.class).setMasterKillSwitch(false);
Jenkins.instance.save()

logger.info("Setting Time Zone to be UTC")
System.setProperty('org.apache.commons.jelly.tags.fmt.timeZone', 'UTC')
