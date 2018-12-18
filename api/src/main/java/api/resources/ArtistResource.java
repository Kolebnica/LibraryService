package api.resources;

import beans.crud.ArtistBean;
import com.kumuluz.ee.logs.cdi.Log;
import entities.Artist;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("artists")
@ApplicationScoped
@Tags(value = @Tag(name = "artists"))
@Log
public class ArtistResource {

    @Inject
    ArtistBean artistBean;

    @Operation(
            summary = "Get artists",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of artists"),
            }
    )
    @GET
    public Response getArtists() {
        return Response.ok(artistBean.getArtists()).build();
    }

    @Operation(
            summary = "Get artist",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Artist with given ID"),
                    @ApiResponse(responseCode = "404", description = "Artist with ID not found"),
            },
            parameters = {
                    @Parameter(name = "artistId", in = ParameterIn.PATH),
            }
    )
    @GET
    @Path("{artistId}")
    public Response getArtistById(@PathParam("artistId") int artistId) {
        Artist a = artistBean.getArtist(artistId);
        if (a == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(a).build();
    }

    //

    @Operation(
            summary = "Create artist",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Artist created"),
                    @ApiResponse(responseCode = "409", description = "Conflict when trying to create artist"),
            },
            requestBody = @RequestBody(content = {@Content(schema = @Schema(implementation = Artist.class))})
    )
    @POST
    public Response createArtist(Artist artist) {
        artist = artistBean.insertArtist(artist);
        if (artist == null) {
            return Response.status(Response.Status.CONFLICT).entity(artist).build();
        } else {
            return Response.status(Response.Status.CREATED).entity(artist).build();
        }
    }

    @Operation(
            summary = "Update artist",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Artist updated"),
                    @ApiResponse(responseCode = "404", description = "Artist with ID does not exist")
            }
    )
    @PUT
    @Path("{artistId}")
    public Response updateArtist(@PathParam("artistId") int artistId, Artist artist) {

        artist = artistBean.updateArtist(artistId, artist);

        if (artist == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.status(Response.Status.OK).entity(artist).build();
        }
    }

    @Operation(
            summary = "Delete artist",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Artist deleted"),
                    @ApiResponse(responseCode = "404", description = "Artist with ID does not exist")
            }
    )
    @DELETE
    @Path("{artistId}")
    public Response deleteArtist(@PathParam("artistId") int artistId) {

        boolean deleted = artistBean.deleteArtist(artistId);

        if (deleted) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
