package pl.agh.jtp.lab06_home.versionControl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation defines in with type of license annotated plugin is available.
 * It takes one or more argument.
 * When some plugin is available under more than one license type
 * put all kind of licenses in curly brackets and separate them with semicolons.
 * e.g. @License(licenseType = {LicenseType.STANDARD, LicenseType.ENTERPRISE})
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface License {
    LicenseType[] licenseType();
}
