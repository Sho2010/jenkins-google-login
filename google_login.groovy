import jenkins.*
import hudson.*
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.common.*
import com.cloudbees.plugins.credentials.domains.*
import com.cloudbees.jenkins.plugins.sshcredentials.impl.*
import hudson.plugins.sshslaves.*;
import hudson.model.*
import jenkins.model.*
import hudson.security.*
import org.jenkinsci.plugins.googlelogin.GoogleOAuth2SecurityRealm

def instance = Jenkins.getInstance()
def env = System.getenv()

def clientId = env["GOOGLE_APP_CLIENT_ID"]
def clientSecret = env["GOOGLE_APP_SECRET"]
def domain = env["GOOGLE_ACCOUNT_DOMAIN"]

def googleRealm = new GoogleOAuth2SecurityRealm(clientId, clientSecret, domain)
instance.setSecurityRealm(googleRealm)
instance.save()

//A special group "authenticated" is also available, which represents all authenticated (logged in) users.
def strategy = new GlobalMatrixAuthorizationStrategy()
strategy.add(Jenkins.ADMINISTER, "authenticated")
instance.setAuthorizationStrategy(strategy)
instance.save()

