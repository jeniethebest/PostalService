package edu.uic.cs.postalservice.rest;

import edu.uic.cs.postalservice.dao.ManageMain;
import edu.uic.cs.postalservice.dao.ManageDependent;
import edu.uic.cs.postalservice.model.UserInformation;
import edu.uic.cs.postalservice.model.UserRoles;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: Ashwath
 * Date: 11/11/13
 * Time: 6:59 PM
 * To change this template use File | Settings | File Templates.
 */

@Path("v1/UserPost")
public class V1_UserPost {
    ManageMain mPack = ManageMain.getInstance();
    ManageDependent mDep = ManageDependent.getInstance();


    // Post method which is used to post the user information and save in the database

    @POST
    @Path("createUser")
    @Consumes({"application/x-www-form-urlencoded"})
    @Produces(MediaType.TEXT_PLAIN)
    public String postPackageInfo(@FormParam("userFirstName") String userfirstname,
                                  @FormParam("userLastName") String userlastname,
                                  @FormParam("userDob") String userDob,
                                  @FormParam("userEmail") String emailAddress,
                                  @FormParam("userLocation") String city,
                                  @FormParam("userRole") String rolename,
                                  @FormParam("userLogin") String userLogin,
                                  @FormParam("userPassword") String userPassword
                                  )
    {
        String returnData = null;
        UserRoles userRoles = mDep.getRoleObj(rolename);
        UserInformation obj1 = new UserInformation(userfirstname,userlastname,userDob,emailAddress,city,userRoles,userLogin,userPassword,null);

        return returnData;
    }


    }
