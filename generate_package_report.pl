print "content-type:text/html\n\n";

use LWP::Simple;
use Data::Dumper;
use JSON;
use Try::Tiny;


my $url = get("http://localhost:8080/PostalService/rest/v1/Package/packageGet");
print "The file has been retrieved\n";

my $json_values = decode_json($url);
print  Dumper $json_values;


