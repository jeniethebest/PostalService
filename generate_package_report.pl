print "content-type:text/html\n\n";

# Set coding and compilation standards
use strict;
use warnings;

# Used for JSON parsing
use JSON;
use Try::Tiny;

# Used to retrieve the contents from the url
use LWP::Simple;

# Used for debugging standards & perl default libraries
use Data::Dumper;
use Getopt::Long;

# Used for creating the HTML tables
use HTML::Table;
use MIME::Lite;
use MIME::Base64;
use Authen::SASL;


my $program_name = 'generate_package_report.pl';
my $email_format ='gmail.com';
my $generate_report_hash = {};
my ($url, $json_values, $code);

my %opts = ();
GetOptions(\%opts,
	'help|h',
	'debug',
	'email',
	'alluserpackages',
	'userpackages|u=s',
	'from|f=s',
	'to|t=s',
	'cc=s',
	);

my $mail_from ='ashwath.sundar@gmail.com';
my $mail_to = 'ashwath.sundar@gmail.com';
my $mail_cc = 'ashwath.sundar@gmail.com';

if( defined($opts{help}) )
{
printf('
		The following options are available:
		--help	
		--debug		# To trace the errors
		
		Report the detailed list of all users packages:
		--alluserpackages
		
		Reports the detailed list of all the packages for a particular user:
		--userpackages	# Specify the id of the user to retrieve the list of user packages
		
		Report based on the status of the package
		--status 		# Specify the status of the package //Used along with alluserpackages

		Report the details list of user packages based on their id:
		--userinfo		# Provide the id of the user to generate the report

		Email the generated report:
		--email 			# Option set to send the generated report
		--to  			# Specify the email address of the receiver
		--from 		  	# Specify the email address of the sender

		--debug 		# Set this option to show the debug information for the process

');
	exit;
}

if( defined($opts{email} ) )
{
	if(defined($opts{from} ) )
	{
		my $check_flag = &check_email_domain($opts{from});
		die "Enter valid --from address" if($check_flag == 0);
	}
	
	if(defined($opts{to} ) )
	{
		my $check_flag = &check_email_domain($opts{to});
		die "Enter valid --to address" if($check_flag == 0);
	}

	if(defined($opts{cc} ) )
	{
		my $check_flag = &check_email_domain($opts{cc});
		die "Enter valid --cc address" if($check_flag == 0);
	}
	$code = 'password';
}

if( defined($opts{alluserpackages} ) )
{
	print "Executing the alluserpackages module\n";
	my @alluserpackages = ();
	$generate_report_hash->{alluserpackages}->{all_user_package_count} = \@alluserpackages;

	my $get_url = get("http://localhost:8080/PostalService-1.0-SNAPSHOT/rest/v1/User/userInformationGet");

	my $json_values = decode_json($get_url);

	# This below value will save the total number of users.
	$generate_report_hash->{alluserpackages}->{size} = scalar @$json_values;

	foreach my $user_details(@$json_values){
		my $name_packagecount = {};
		$name_packagecount->{user_first_name} =  $user_details->{user_first_name};
		$name_packagecount->{user_last_name} = $user_details->{user_last_name};
		$name_packagecount->{user_email} =  $user_details->{user_email};
		$name_packagecount->{user_package_count} = scalar @{$user_details->{user_packages}};
		push(@alluserpackages,$name_packagecount);
	}

	print  Dumper $generate_report_hash if(defined($opts{debug} ) );
	# $generate_report_hash->{alluserpackages} 
}

if( defined($opts{userpackages} ) )
{
	print "Executing the userpackages module\n";

	my @userpackages = ();
	my $user_id = $opts{userpackages};

	my $user_string = 'http://localhost:8080/PostalService-1.0-SNAPSHOT/rest/v1/User/userInformationGet/';
	my $get_url = get($user_string);
	my $json_values = decode_json($get_url);


	foreach my $user_details(@$json_values){
		my $name_package = {};
		my @each_user_package;
		$name_package->{user_packages} = \@each_user_package;

		$name_package->{user_first_name} =  $user_details->{user_first_name};
		$name_package->{user_last_name} = $user_details->{user_last_name};
		$name_package->{user_email} =  $user_details->{user_email};
		$name_package->{user_package_count} = scalar @{$user_details->{user_packages}};

		foreach my $package_info(@{$user_details->{user_packages}} )
		{
			my $package_details = {};
			$package_details->{source_address} = $package_info->{source_address}->{city};
			$package_details->{destination_address} = $package_info->{destination_address}->{city};
			$package_details->{package_type} = $package_info->{package_type}->{package_name};
			$package_details->{container_info} = $package_info->{container_info};
			$package_details->{status_info} = $package_info->{status_info};
 			push(@each_user_package,$package_details);
		}
		push(@userpackages,$name_package);
	}
	$generate_report_hash->{userpackages}->{user_package_info} = \@userpackages;
	print  Dumper $generate_report_hash if(defined($opts{debug} ) );
}

if(defined($opts{email}) )
{
	print "inside create table module\n\n";
	my $final_table = new HTML::Table;
	$final_table->setCellSpacing(10);
	$final_table->setCellPadding(4);
	my $row_index =1;

	if(defined($opts{alluserpackages} ) )
	{
		my $inner_row_index = 1;
		my $total_users = 0;
		my $total_packages = 0;
		my @table_header = qw/FirstName LastName Email PackageCount/;

		# Creating table having information about all the users
		my $all_user_package_table = new HTML::Table(
							-rules=>'all',
							-border=>1,
							-style=>'color:black'
						);
		$all_user_package_table->setCellPadding(4);
		$all_user_package_table->setCellSpacing(1);
		$all_user_package_table->setCaption("Report for all the user package information","top");
		$all_user_package_table->addRow(@table_header);
		$all_user_package_table->setRowBGColor($inner_row_index,"LightGray");

		foreach my $user_results( @{$generate_report_hash->{alluserpackages}->{all_user_package_count}} )
		{
			$total_users++;
			$inner_row_index++;			
			$all_user_package_table->addRow(
							$user_results->{user_first_name},
							$user_results->{user_last_name},
							$user_results->{user_email},
							$user_results->{user_package_count},
				);
			$all_user_package_table->setCellBGColor($inner_row_index,4,'red') if( $user_results->{user_package_count} > 2);
			
		}

		# Creating table having summarized details of all the users
		my $all_user_package_table_total = new HTML::Table(
								-rules=>'all',
								-border=>1,
								-style=>'color:black',
								);
		$all_user_package_table_total->setCellPadding(4);
		$all_user_package_table_total->setCellSpacing(1);
		$all_user_package_table_total->setCaption("Summarized report for all user packages","top");
		$all_user_package_table_total->addRow('Total Users','Total Packages');
		$all_user_package_table_total->setRowBGColor(1,"LightGray");

		$all_user_package_table_total->addRow($total_users,$total_packages);

		$final_table->setCell($row_index,1,$all_user_package_table);
		$final_table->setCell($row_index,2,$all_user_package_table_total);
		$row_index++;
	}

	if(defined($opts{userpackages} ) )
	{
		my $inner_row_index = 1;
		my $total_users = 0;
		my $total_packages = 0;
		my @table_header = qw/FirstName LastName Email PackageCount PackageInformation/;

		# Creating table having information about all the users
		my $all_user_package_table = new HTML::Table(
							-rules=>'all',
							-border=>1,
							-style=>'color:black'
						);
		$all_user_package_table->setCellPadding(4);
		$all_user_package_table->setCellSpacing(1);
		$all_user_package_table->setCaption("Report for the package information for specific users","top");
		$all_user_package_table->addRow(@table_header);
		$all_user_package_table->setRowBGColor($inner_row_index,"LightGray");

		foreach my $user_results( @{$generate_report_hash->{userpackages}->{user_package_info}} )
		{
			$total_users++;
			$inner_row_index++;
			my $package_table = &createUserPacakgeTable($user_results->{user_packages});
			$all_user_package_table->addRow(
							$user_results->{user_first_name},
							$user_results->{user_last_name},
							$user_results->{user_email},
							$user_results->{user_package_count},
							$package_table,
				);			
		}

		$final_table->setCell($row_index,1,$all_user_package_table);
		$row_index++;		
	}

	print Dumper $final_table;

	my $msg = MIME::Lite->new(
		From 	 => $mail_from,
		To 	 => $mail_to,
		Cc   	 => $mail_cc,
		Type     =>'multipart/mixed',
		Subject =>'Package detail report',     
		);
	
	$msg->attach(
		Type 		=> 'text/html',
		Encoding	=> 'base64',
		Data 		=> $final_table,
		);

	$msg->send("smtp","smtp.gmail.com",AuthUser=>'ashwath.sundar@gmail.com', AuthPass=>$code);
	print Dumper $msg;
}


################################
# Defining functions

sub check_email_domain{
	my $email_ids_string = shift;
	my @email_ids = split(/,/,$email_ids_string);

	foreach my $email_id(@email_ids)
	{
		my $candidate_domain;
		$candidate_domain = $1 if($email_id =~ m/@(.*)$/i);
		if($email_format eq $candidate_domain){
			return 1;
		}else{
			return 0;
		}
	}
}

sub createUserPacakgeTable{
	my $package_information = shift;
	my @table_header = qw/SourceCity DestinationCity PackageType ContainerType StatusType/;

	# Creating table having information about all the users
	my $user_package_table = new HTML::Table(
						-rules=>'all',
						-border=>1,
						-style=>'color:black'
					);

	$user_package_table->setCellPadding(4);
	$user_package_table->setCellSpacing(1);
	$user_package_table->addRow(@table_header);

	foreach my $package_info(@{$package_information})
	{
		$user_package_table->addRow(
							$package_info->{source_address},
							$package_info->{destination_address},
							$package_info->{package_type},
							$package_info->{container_info},
							$package_info->{status_info}
							);
	}

	return $user_package_table;
}




